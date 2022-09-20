package es.plexus.android.data.common.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.plexus.android.data.common.contract.DataLayerContract
import es.plexus.android.data.common.logger.Logger
import es.plexus.android.data.common.logger.SyncLogger
import es.plexus.android.data.common.networkmanager.NetworkManager
import es.plexus.android.data.common.networkmanager.NetworkManagerImpl
import es.plexus.android.data.datasources.superHeroesAPI.SuperHerosRemoteDataSource
import es.plexus.android.data.datasources.superHeroesDB.SuperHeroesPersistenceDataSource
import es.plexus.android.data.datasources.superHeroesDB.db.DB_NAME
import es.plexus.android.data.datasources.superHeroesDB.db.HeroesDatabase
import es.plexus.android.data.repository.SuperHeroesDataRepository
import es.plexus.android.domain.repository.SuperHeroesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object DataProperties {
    const val API_SERVICE_TAG = "superHeroesApiService"
    const val API_BASE_URL = "https://gateway.marvel.com:443/v1/public/"
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @IODispatcherNamed
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesSyncLogger(): Logger = SyncLogger()


    //region Repository
    @Provides
    @Singleton
    fun provideSuperHeroesRepositoryy(
        remoteDatasource: DataLayerContract.SuperHeroesDataSource.Remote,
        persistenceDataSource: DataLayerContract.SuperHeroesDataSource.Persistence
    ): SuperHeroesRepository {
        return SuperHeroesDataRepository(
            remoteDataSource = remoteDatasource,
            persistenceDataSource
        )
    }


    @Provides
    @Singleton
    @NetworkManagerNamed
    fun provideSNetworkManager(
        @ApplicationContext context: Context
    ): NetworkManager {
        return NetworkManagerImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    @SuperHeroApiClientNamed
    fun provideSuperHeroApi(
        @AuthOkHttpClientNamed httpClient: OkHttpClient
    ): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .baseUrl(DataLayerContract.SuperHeroesDataSource.API_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @AuthOkHttpClientNamed
    fun provideAuthOkHttpClient(
    ): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        return clientBuilder.build()
    }


    @Provides
    fun provideSuperHerosDataSource(
        database: HeroesDatabase
    ): DataLayerContract.SuperHeroesDataSource.Persistence {
        return SuperHeroesPersistenceDataSource(
            database = database
        )
    }

    @Provides
    fun provideSuperHerosRemoteDataSource(
        @NetworkManagerNamed networkManager: NetworkManager,
        @SuperHeroApiClientNamed apiClient: Retrofit,
    ): DataLayerContract.SuperHeroesDataSource.Remote {
        return SuperHerosRemoteDataSource(
            apiClient = apiClient,
            networkManager = networkManager
        )
    }

    @Provides
    @Singleton
    fun provideSuperHeroDatabase(
        @ApplicationContext context: Context
    ): HeroesDatabase {
        return Room.databaseBuilder(
            context,
            HeroesDatabase::class.java, DB_NAME
        )
            .build()
    }


    //endregion

}