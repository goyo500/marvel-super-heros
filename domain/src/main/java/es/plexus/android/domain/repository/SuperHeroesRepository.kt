package es.plexus.android.domain.repository

import arrow.core.Either
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList

interface SuperHeroesRepository {

    suspend fun fetchSuperHeroesListData(): Either<Failure, Boolean>
    suspend fun fetchSuperHeroDetailData(id: Int): Either<Failure, Int>

    suspend fun saveSuperHeroesListData(data: SuperHeroList): Either<Failure, Boolean>
    suspend fun saveSuperHeroesDetailData(data: SuperHero): Either<Failure, Int>

    suspend fun getSuperHeroesListPersistedData(): Either<Failure, SuperHeroList>
    suspend fun getSuperHeroesDetailPersistedData(id: Int): Either<Failure, SuperHero>

}