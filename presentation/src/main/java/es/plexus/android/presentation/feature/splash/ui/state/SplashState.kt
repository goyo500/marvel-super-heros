package es.plexus.android.presentation.feature.splash.ui.state

import es.plexus.android.domain.model.Failure
import es.plexus.android.presentation.base.BaseState

sealed class SplashState : BaseState(){
    object GoToList : SplashState()
    class ShowError(val failure : Failure) : SplashState()
}