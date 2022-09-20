package es.plexus.android.presentation.feature.heroes.list.ui.state

import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.presentation.base.BaseState


sealed class HerosListState : BaseState() {

    class LoadHeros(val data: SuperHeroList) : HerosListState()
    class GoToDetail(val id: Int) : HerosListState()
    class ShowError(val failure: Failure) : HerosListState()

}