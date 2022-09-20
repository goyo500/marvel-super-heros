package es.plexus.android.presentation.feature.heroes.list.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.plexus.android.domain.feature.HeroesListDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.presentation.base.BaseMvvmViewModel
import es.plexus.android.presentation.base.ScreenState
import es.plexus.android.presentation.feature.heroes.list.ui.state.HerosListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class HeroesListViewModel @Inject constructor(
    bridge: HeroesListDomainLayerBridge
) : BaseMvvmViewModel<HeroesListDomainLayerBridge, HerosListState>(bridge = bridge) {

    fun onViewCreated() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            bridge.getSuperHeroesList().fold(::handleError, ::handleListSuccess)
        }
    }

    fun onClickItem(id: Int) {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            bridge.getSuperHeroDetail(id)
                .fold({ handleDetailError(id) }, { handleDetailSuccess(it.id) })
        }
    }

    private fun handleListSuccess(response: SuperHeroList) {
        _screenState.value = ScreenState.Render(HerosListState.LoadHeros(response))
    }

    private fun handleDetailSuccess(response: Int) {
        _screenState.value = ScreenState.Render(HerosListState.GoToDetail(response))
    }

    private fun handleDetailError(id: Int) {
        viewModelScope.launch {
            bridge.synchronizeSuperHeroDetail(id).fold(::handleError, ::handleDetailSuccess)
        }
    }

    private fun handleError(failure: Failure) {
        _screenState.value = ScreenState.Render(HerosListState.ShowError(failure))
    }

    fun cleanDetailData() {
        _screenState.value = ScreenState.Idle
    }
}