package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.usecase.GetSuperHeroesListPersistedDataUc
import es.plexus.android.domain.usecase.SynchronizeSuperHeroesListDataUc
import javax.inject.Inject

class SplashDomainLayerBridge @Inject constructor(
    private val synchronizeSuperHeroesListUc: SynchronizeSuperHeroesListDataUc,
    private val getSuperHeroesListUc: GetSuperHeroesListPersistedDataUc,
) : BaseDomainLayerBridge {

    suspend fun synchronizeSuperHeroesList(): Either<Failure, Boolean> =
        synchronizeSuperHeroesListUc.run()

    suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList> =
        getSuperHeroesListUc.run()

}