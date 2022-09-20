package es.plexus.android.presentation.feature.splash.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.plexus.android.domain.feature.SplashDomainLayerBridge
import es.plexus.android.domain.model.Failure
import es.plexus.android.presentation.base.BaseMvvmView
import es.plexus.android.presentation.base.ScreenState
import es.plexus.android.presentation.databinding.ActivitySplashBinding
import es.plexus.android.presentation.feature.common.ui.ErrorDialogFragment
import es.plexus.android.presentation.feature.heroes.list.ui.view.HeroesActivity
import es.plexus.android.presentation.feature.splash.ui.state.SplashState
import es.plexus.android.presentation.feature.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity(),
    BaseMvvmView<SplashViewModel, SplashDomainLayerBridge, SplashState> {


    override val viewModel: SplashViewModel by viewModels()
    private lateinit var viewBinding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        initModel()
        setContentView(viewBinding.root)
        viewModel.onViewCreated()
    }


    override fun processRenderState(renderState: SplashState) {
        when (renderState) {
            is SplashState.GoToList -> {
                goToListActivity()
            }
            is SplashState.ShowError -> showError(renderState.failure)
        }
    }


    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Render<SplashState> -> processRenderState(screenState.renderState)
                    else -> {}
                }
            }
        }
    }

    private fun goToListActivity() {
        startActivity(Intent(this, HeroesActivity::class.java))
        finish()
    }

    private fun showError(failure: Failure) {
        ErrorDialogFragment({ finish() }, failure.message).show(
            this.supportFragmentManager,
            ErrorDialogFragment::javaClass.name
        )
    }
}