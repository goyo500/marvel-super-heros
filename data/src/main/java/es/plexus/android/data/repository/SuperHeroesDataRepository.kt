package es.plexus.android.data.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import es.plexus.android.data.common.contract.DataLayerContract
import es.plexus.android.data.datasources.superHeroesDB.mappers.toDomain
import es.plexus.android.data.datasources.superHeroesDB.mappers.toEntity
import es.plexus.android.data.datasources.superHeroesDB.mappers.toEntityList
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.repository.SuperHeroesRepository

internal class SuperHeroesDataRepository(
    private val remoteDataSource: DataLayerContract.SuperHeroesDataSource.Remote,
    private val persistenceDataSource: DataLayerContract.SuperHeroesDataSource.Persistence
) : SuperHeroesRepository {

    override suspend fun fetchSuperHeroesListData(): Either<Failure, Boolean> =
        remoteDataSource.fetchSuperHeroesListData().flatMap { data ->
            saveSuperHeroesListData(data)
        }

    override suspend fun fetchSuperHeroDetailData(id: Int): Either<Failure, Int> =
        remoteDataSource.fetchSuperHeroDetailData(id).flatMap { data ->
            saveSuperHeroesDetailData(data.results.first())
        }

    override suspend fun saveSuperHeroesListData(data: SuperHeroList): Either<Failure, Boolean> =
        persistenceDataSource.saveSuperHeroesListData(data.toEntityList())

    override suspend fun saveSuperHeroesDetailData(data: SuperHero): Either<Failure, Int> =
        persistenceDataSource.saveSuperHeroesDetailData(data.toEntity())

    override suspend fun getSuperHeroesListPersistedData(): Either<Failure, SuperHeroList> =
        persistenceDataSource.getSuperHeroesListData().flatMap { response ->
            response.toDomain().right()
        }

    override suspend fun getSuperHeroesDetailPersistedData(id: Int): Either<Failure, SuperHero> {
        val response = persistenceDataSource.getSuperHeroDetailData(id)
        if (response.isRight()) {
            response.map {
                return it.toDomain().right()
            }
        }
        return Failure.NoData().left()

    }
}