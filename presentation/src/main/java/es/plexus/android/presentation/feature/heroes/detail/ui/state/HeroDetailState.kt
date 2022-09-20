package es.plexus.android.presentation.feature.heroes.detail.ui.state

import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.presentation.base.BaseState

sealed class HeroDetailState : BaseState() {

    class LoadHero(val data: SuperHero) : HeroDetailState()
    class ShowError(val failure: Failure) : HeroDetailState()

}