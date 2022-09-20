package es.plexus.android.data.datasources.superHeroesAPI.mappers

import es.plexus.android.data.datasources.superHeroesAPI.model.FailureApi
import es.plexus.android.domain.model.Failure

internal fun FailureApi.dtoToBoFailure(): Failure = when (this) {
    is FailureApi.Unauthorized -> Failure.Unauthorized(this.message,this.code)
    FailureApi.NoData -> Failure.NoData(this.message,this.code)
    FailureApi.NoNetwork -> Failure.NoNetwork(this.message,this.code)
    is FailureApi.Request -> Failure.Request(this.message,this.code)
    FailureApi.Unknown -> Failure.Unknown(this.message,this.code)
}