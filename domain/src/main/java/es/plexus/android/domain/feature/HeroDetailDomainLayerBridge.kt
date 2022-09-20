package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.usecase.GetSuperHeroDetailUc
import javax.inject.Inject


class HeroDetailDomainLayerBridge @Inject constructor(
    val getSuperHeroDetailUc: GetSuperHeroDetailUc
) : BaseDomainLayerBridge {

    suspend fun getSuperHeroDetail(params: Int): Either<Failure, SuperHero> =
        getSuperHeroDetailUc.run(params)

}