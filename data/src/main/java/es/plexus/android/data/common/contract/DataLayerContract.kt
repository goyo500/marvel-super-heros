package es.plexus.android.data.common.contract

import arrow.core.Either
import es.plexus.android.data.datasources.superHeroesDB.model.HeroDetailEntity
import es.plexus.android.data.datasources.superHeroesDB.model.HeroEntity
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList

interface DataLayerContract {

    interface SuperHeroesDataSource {

        companion object {
            const val API_SERVICE_TAG = "superHeroesApiService"
            const val API_BASE_URL = "https://gateway.marvel.com:443/v1/public/"
        }

        interface Remote {
            suspend fun fetchSuperHeroesListData(): Either<Failure, SuperHeroList>
            suspend fun fetchSuperHeroDetailData(id: Int): Either<Failure, SuperHeroList>
        }

        interface Persistence {
            suspend fun saveSuperHeroesListData(data: ArrayList<HeroEntity>): Either<Failure, Boolean>
            suspend fun saveSuperHeroesDetailData(data: HeroDetailEntity): Either<Failure, Int>
            suspend fun getSuperHeroesListData(): Either<Failure, List<HeroEntity>>
            suspend fun getSuperHeroDetailData(id: Int): Either<Failure, HeroDetailEntity>
        }
    }

}