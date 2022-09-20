package es.plexus.android.domain.usecase

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.repository.SuperHeroesRepository
import javax.inject.Inject

const val SYNCHRONIZE_SUPER_HEROES_LIST_UC_TAG = "synchronizeSuperHeroesListUc"

class SynchronizeSuperHeroesListDataUc @Inject constructor(
    private val superHeroesRepository: SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Any, Boolean> {

    override suspend fun run(params: Any?): Either<Failure, Boolean> =
        superHeroesRepository.fetchSuperHeroesListData()
}
