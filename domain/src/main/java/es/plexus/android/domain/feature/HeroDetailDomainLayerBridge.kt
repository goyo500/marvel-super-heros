package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero

const val HERO_DETAIL_BRIDGE_TAG = "heroDetailDomainLayerBridge"

interface HeroDetailDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun getSuperHeroDetail(params : Int): Either<Failure, SuperHero>
}

internal class HeroDetailDomainLayerBridgeImpl(
    private val getSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, SuperHero>
) : HeroDetailDomainLayerBridge {

    override suspend fun getSuperHeroDetail(params: Int): Either<Failure, SuperHero> =
        getSuperHeroDetailUc.run(params)

}