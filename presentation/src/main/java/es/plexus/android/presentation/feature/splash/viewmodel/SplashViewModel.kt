package es.plexus.android.presentation.feature.splash.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.plexus.android.domain.feature.SplashDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.presentation.base.BaseMvvmViewModel
import es.plexus.android.presentation.base.ScreenState
import es.plexus.android.presentation.feature.splash.ui.state.SplashState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class SplashViewModel @Inject constructor(bridge: SplashDomainLayerBridge) :
    BaseMvvmViewModel<SplashDomainLayerBridge, SplashState>(bridge = bridge) {

    fun onViewCreated() {
        viewModelScope.launch {
            bridge.getSuperHeroesList().fold({}, ::handleSuccessGet)
        }
    }

    private fun handleSuccess(response: Boolean) {
        _screenState.value = ScreenState.Render(SplashState.GoToList)
    }

    private fun handleSuccessGet(response: SuperHeroList) {
        if (response.results.isEmpty()) {
            viewModelScope.launch {
                bridge.synchronizeSuperHeroesList().fold(::handleError, ::handleSuccess)
            }
        } else {
            handleSuccess(true)
        }
    }

    private fun handleError(failure: Failure) {
        _screenState.value = ScreenState.Render(SplashState.ShowError(failure))
    }

}