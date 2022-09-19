package es.plexus.android.data.datasources.superHeroesDB

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.plexus.android.data.common.contract.DataLayerContract
import es.plexus.android.data.datasources.superHeroesAPI.mappers.dtoToBoFailure
import es.plexus.android.data.datasources.superHeroesAPI.model.FailureApi
import es.plexus.android.data.datasources.superHeroesDB.db.HeroesDatabase
import es.plexus.android.data.datasources.superHeroesDB.model.HeroDetailEntity
import es.plexus.android.data.datasources.superHeroesDB.model.HeroEntity
import es.plexus.android.domain.model.Failure

internal class SuperHeroesPersistenceDataSource(
    private val database: HeroesDatabase
) : DataLayerContract.SuperHeroesDataSource.Persistence {

    override suspend fun saveSuperHeroesListData(data: ArrayList<HeroEntity>): Either<Failure, Boolean> {
        data.forEach { hero ->
            database.appDao().insertHero(hero)
        }
        return true.right()
    }

    override suspend fun saveSuperHeroesDetailData(data: HeroDetailEntity): Either<Failure, Int> {
        database.appDao().insertHeroDetail(data)
        return data.heroId.right()
    }

    override suspend fun getSuperHeroesListData(): Either<Failure, List<HeroEntity>> =
        database.appDao().getHeroesList().right()

    override suspend fun getSuperHeroDetailData(id: Int): Either<Failure, HeroDetailEntity> =
        database.appDao().getHeroDetail(id)?.right() ?: FailureApi.NoData.dtoToBoFailure().left()
}
