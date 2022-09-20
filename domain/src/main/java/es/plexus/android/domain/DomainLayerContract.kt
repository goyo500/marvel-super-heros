package es.plexus.android.domain

import arrow.core.Either
import es.plexus.android.domain.model.Failure
import kotlinx.coroutines.*

interface DomainLayerContract {

    interface Presentation {

        interface UseCase<in T, out S> {
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<Failure, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                scope.launch { onResult(withContext(dispatcherWorker) { run(params) }) }
            }

            suspend fun run(params: T? = null): Either<Failure, S>
        }
    }

    interface Data {

        companion object {
            const val SUPER_HEROES_REPOSITORY_TAG = "superHeroesRepository"
        }


    }
}