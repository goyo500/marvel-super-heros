package es.plexus.android.presentation.feature.heroes.detail.viewmodel

import androidx.lifecycle.viewModelScope
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.feature.HeroDetailDomainLayerBridge
import es.plexus.android.presentation.base.BaseMvvmViewModel
import es.plexus.android.presentation.base.ScreenState
import es.plexus.android.presentation.feature.heroes.detail.ui.state.HeroDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HeroDetailViewModel(
    bridge: HeroDetailDomainLayerBridge
) : BaseMvvmViewModel<HeroDetailDomainLayerBridge, HeroDetailState>(bridge = bridge) {

    fun onViewCreated(id : Int){
        viewModelScope.launch {
            bridge.getSuperHeroDetail(id).fold(::handleError,::handleSuccess)
        }
    }

    private fun handleSuccess(response : SuperHero){
        _screenState.value = ScreenState.Render(HeroDetailState.LoadHero(response))
    }

    private fun handleError(failure: Failure) {
        _screenState.value = ScreenState.Render(HeroDetailState.ShowError(failure))
    }

}