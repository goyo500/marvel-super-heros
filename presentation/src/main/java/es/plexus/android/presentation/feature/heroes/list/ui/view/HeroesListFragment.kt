package es.plexus.android.presentation.feature.heroes.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.feature.HeroesListDomainLayerBridge
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.presentation.base.BaseMvvmView
import es.plexus.android.presentation.base.ScreenState
import es.plexus.android.presentation.databinding.FragmentHeroesListBinding
import es.plexus.android.presentation.feature.common.ui.ErrorDialogFragment
import es.plexus.android.presentation.feature.heroes.list.ui.adapter.HeroesListAdapter
import es.plexus.android.presentation.feature.heroes.list.ui.state.HerosListState
import es.plexus.android.presentation.feature.heroes.list.viewmodel.HeroesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HeroesListFragment : Fragment(),
    BaseMvvmView<HeroesListViewModel, HeroesListDomainLayerBridge, HerosListState> {

    override val viewModel: HeroesListViewModel by viewModels()
    private lateinit var viewBinding: FragmentHeroesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHeroesListBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        viewModel.onViewCreated()
    }

    override fun processRenderState(renderState: HerosListState) {
        when (renderState) {
            is HerosListState.LoadHeros -> {
                renderData(renderState.data)
            }
            is HerosListState.GoToDetail -> {
                goToDetail(renderState.id)
            }
            is HerosListState.ShowError -> showError(renderState.failure)
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<HerosListState> -> processRenderState(screenState.renderState)
                    ScreenState.Loading -> {
                        manageLoading(View.VISIBLE)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun goToDetail(id: Int) {
        val heroId =
            HeroesListFragmentDirections.actionHeroesListFragmentToHeroDetailFragment(id)
        Navigation.findNavController(requireView()).navigate(heroId)
        viewModel.cleanDetailData()
    }

    private fun renderData(data: SuperHeroList) {
        with(viewBinding) {
            rvHeroesList.adapter = HeroesListAdapter(
                data.results
            ) { data -> viewModel.onClickItem(data.id) }
            rvHeroesList.layoutManager = LinearLayoutManager(activity)
            rvHeroesList.visibility = View.VISIBLE
        }
        manageLoading(View.GONE)
    }

    private fun manageLoading(visibilityValue: Int) {
        viewBinding.lavLoading.visibility = visibilityValue
    }

    private fun showError(failure: Failure) {
        activity?.supportFragmentManager?.let {
            ErrorDialogFragment({}, failure.message).show(
                it,
                ErrorDialogFragment::javaClass.name
            )
        }
        manageLoading(View.GONE)
    }

}