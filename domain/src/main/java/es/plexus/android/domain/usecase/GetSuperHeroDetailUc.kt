package es.plexus.android.domain.usecase

import arrow.core.Either
import arrow.core.left
import es.plexus.android.domain.DomainLayerContract
import es.plexus.android.domain.model.Failure
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.repository.SuperHeroesRepository
import javax.inject.Inject

const val GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG = "getSuperHeroDetailPersistedUc"

class GetSuperHeroDetailUc @Inject constructor(
    private val superHeroesRepository: SuperHeroesRepository
) : DomainLayerContract.Presentation.UseCase<Int, SuperHero> {

    override suspend fun run(params: Int?): Either<Failure, SuperHero> =
        params?.let { id ->
            superHeroesRepository.getSuperHeroesDetailPersistedData(id)
        } ?: Failure.NoData().left()

}
