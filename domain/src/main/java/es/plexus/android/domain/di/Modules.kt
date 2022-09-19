package es.plexus.android.domain.di

import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.DomainLayerContract.Data.Companion.SUPER_HEROES_REPOSITORY_TAG
import es.plexus.android.domain.feature.*
import es.plexus.android.domain.feature.HeroDetailDomainLayerBridgeImpl
import es.plexus.android.domain.feature.HeroesListDomainLayerBridgeImpl
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val bridgeModule = module {

    factory<SplashDomainLayerBridge>(named(name = SPLASH_BRIDGE_TAG)) {
        SplashDomainLayerBridgeImpl(
            synchronizeSuperHeroesListUc = get(named(name=SYNCHRONIZE_SUPER_HEROES_LIST_UC_TAG)),
            getSuperHeroesListUc = get(named(name=GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)),
        )
    }

    factory<es.plexus.android.domain.feature.HeroesListDomainLayerBridge>(named(name = es.plexus.android.domain.feature.HEROS_LIST_BRIDGE_TAG)) {
        HeroesListDomainLayerBridgeImpl(
            getSuperHeroesListUc = get(named(name=GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)),
            synchronizeSuperHeroDetailUc = get(named(name=SYNCHRONIZE_SUPER_HERO_DETAIL_UC_TAG)),
            getSuperHeroDetailUc = get(named(name=GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG))
        )
    }

    factory<HeroDetailDomainLayerBridge>(named(name = HERO_DETAIL_BRIDGE_TAG)) {
        HeroDetailDomainLayerBridgeImpl(
            getSuperHeroDetailUc = get(named(name=GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG))
        )
    }
}

@ExperimentalCoroutinesApi
val useCaseModule = module {
    factory<DomainLayerContract.Presentation.UseCase<Nothing, Boolean>>(named(name = SYNCHRONIZE_SUPER_HEROES_LIST_UC_TAG)) {
        SynchronizeSuperHeroesListDataUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Int, Int>>(named(name = SYNCHRONIZE_SUPER_HERO_DETAIL_UC_TAG)) {
        SynchronizeSuperHeroDetailUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Nothing, SuperHeroList>>(named(name = GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG)) {
        GetSuperHeroesListPersistedDataUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

    factory<DomainLayerContract.Presentation.UseCase<Int, SuperHero>>(named(name = GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG)) {
        GetSuperHeroDetailUc(superHeroesRepository = get(named(name = SUPER_HEROES_REPOSITORY_TAG)))
    }

}

@ExperimentalCoroutinesApi
val domainLayerModule = listOf(bridgeModule, useCaseModule)
