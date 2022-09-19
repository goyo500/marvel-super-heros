package es.plexus.android.domain.feature

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.base.BaseDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList

const val HEROS_LIST_BRIDGE_TAG = "herosListDomainLayerBridge"

interface HeroesListDomainLayerBridge : BaseDomainLayerBridge {
    suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList>
    suspend fun synchronizeSuperHeroDetail(params : Int): Either<Failure, Int>
    suspend fun getSuperHeroDetail(params : Int): Either<Failure, SuperHero>
}

internal class HeroesListDomainLayerBridgeImpl(
    private val getSuperHeroesListUc: DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroList>,
    private val synchronizeSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, Int>,
    private val getSuperHeroDetailUc: DomainLayerContract.Presentation.UseCase<Int, SuperHero>
) : es.plexus.android.domain.feature.HeroesListDomainLayerBridge {

    override suspend fun getSuperHeroesList(): Either<Failure, SuperHeroList> =
        getSuperHeroesListUc.run()

    override suspend fun synchronizeSuperHeroDetail(params : Int): Either<Failure, Int> =
        synchronizeSuperHeroDetailUc.run(params)

    override suspend fun getSuperHeroDetail(params: Int): Either<Failure, SuperHero> =
        getSuperHeroDetailUc.run(params)

}