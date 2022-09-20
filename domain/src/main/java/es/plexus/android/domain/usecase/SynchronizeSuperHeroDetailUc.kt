package es.plexus.android.domain.usecase

import arrow.core.Either
import arrow.core.left
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.repository.SuperHeroesRepository
import javax.inject.Inject

const val SYNCHRONIZE_SUPER_HERO_DETAIL_UC_TAG = "synchronizeSuperHeroDetailUc"

class SynchronizeSuperHeroDetailUc @Inject constructor(
    private val superHeroesRepository: SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Int, Int> {

    override suspend fun run(params: Int?): Either<Failure, Int> =
        params?.let { id ->
            superHeroesRepository.fetchSuperHeroDetailData(id)
        } ?: Failure.NoData().left()

}
