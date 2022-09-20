package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.usecase.GetSuperHeroDetailUc
import es.plexus.android.domain.usecase.GetSuperHeroesListPersistedDataUc
import es.plexus.android.domain.usecase.SynchronizeSuperHeroDetailUc
import javax.inject.Inject


class HeroesListDomainLayerBridge @Inject constructor(
    val getSuperHeroesListUc: GetSuperHeroesListPersistedDataUc,
    val synchronizeSuperHeroDetailUc: SynchronizeSuperHeroDetailUc,
    val getSuperHeroDetailUc: GetSuperHeroDetailUc
) : BaseDomainLayerBridge {

    suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList> =
        getSuperHeroesListUc.run()

    suspend fun synchronizeSuperHeroDetail(params: Int): Either<Failure, Int> =
        synchronizeSuperHeroDetailUc.run(params)

    suspend fun getSuperHeroDetail(params: Int): Either<Failure, SuperHero> =
        getSuperHeroDetailUc.run(params)

}