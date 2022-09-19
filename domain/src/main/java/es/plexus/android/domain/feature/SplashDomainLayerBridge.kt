package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList

const val SPLASH_BRIDGE_TAG = "splashDomainLayerBridge"

interface SplashDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun synchronizeSuperHeroesList(): Either<Failure, Boolean>
    suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList>
}

internal class SplashDomainLayerBridgeImpl(
    private val synchronizeSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, Boolean>,
    private val getSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroList>,
) : SplashDomainLayerBridge {

    override suspend fun synchronizeSuperHeroesList(): Either<Failure, Boolean> =
        synchronizeSuperHeroesListUc.run()

    override suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList> =
        getSuperHeroesListUc.run()

}