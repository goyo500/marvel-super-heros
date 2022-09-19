package es.plexus.android.data.datasources.superHeroesAPI

import arrow.core.Either
import arrow.core.left
import es.plexus.android.data.BuildConfig
import es.plexus.android.data.common.contract.DataLayerContract
import es.plexus.android.data.common.extensions.safeCall
import es.plexus.android.data.common.networkmanager.NetworkManager
import es.plexus.android.data.datasources.superHeroesAPI.mappers.dtoToBoFailure
import es.plexus.android.data.datasources.superHeroesAPI.mappers.toDomain
import es.plexus.android.data.datasources.superHeroesAPI.model.FailureApi
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import retrofit2.Retrofit
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

internal class SuperHerosRemoteDataSource(
    private val apiClient: Retrofit,
    private val networkManager: NetworkManager
) : DataLayerContract.SuperHeroesDataSource.Remote {

    private val publicToken = BuildConfig.PUBLIC_KEY
    private val privateToken = BuildConfig.PRIVATE_KEY
    private val ts = Date().time.toString()

    override suspend fun fetchSuperHeroesListData(): Either<Failure, SuperHeroList> =
        if (networkManager.isNetworkAvailable()) {
            try {
                apiClient.create(MarvelSuperHerosApiService::class.java)
                    .getSuperHeroesList(ts, publicToken, getHash(ts + privateToken + publicToken))
                    .safeCall({
                        it.toDomain()
                    })
            } catch (e: Exception) {
                FailureApi.Unknown.dtoToBoFailure().left()
            }
        } else {
            FailureApi.NoNetwork.dtoToBoFailure().left()
        }

    override suspend fun fetchSuperHeroDetailData(id: Int): Either<Failure, SuperHeroList> =
        if (networkManager.isNetworkAvailable()) {
            try {
                apiClient.create(MarvelSuperHerosApiService::class.java)
                    .getSuperHeroDetail(
                        id,
                        ts,
                        publicToken,
                        getHash(ts + privateToken + publicToken)
                    )
                    .safeCall({
                        it.toDomain()
                    })
            } catch (e: Exception) {
                FailureApi.Unknown.dtoToBoFailure().left()
            }
        } else {
            FailureApi.NoNetwork.dtoToBoFailure().left()
        }

    private fun getHash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


}