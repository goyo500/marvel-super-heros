package es.plexus.android.domain.usecase

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.repository.SuperHeroesRepository

const val SYNCHRONIZE_SUPER_HEROES_LIST_UC_TAG = "synchronizeSuperHeroesListUc"

internal class SynchronizeSuperHeroesListDataUc(
    private val superHeroesRepository: SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Any, Boolean> {

    override suspend fun run(params: Any?): Either<Failure, Boolean> =
        superHeroesRepository.fetchSuperHeroesListData()
}
