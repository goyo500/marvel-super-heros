package es.plexus.android.domain.usecase

import arrow.core.Either
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.repository.SuperHeroesRepository

const val GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG = "getSuperHeroesListPersistedUc"

internal class GetSuperHeroesListPersistedDataUc(
    private val superHeroesRepository: SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Any, SuperHeroList> {

    override suspend fun run(params: Any?): Either<Failure, SuperHeroList> =
        superHeroesRepository.getSuperHeroesListPersistedData()
}
