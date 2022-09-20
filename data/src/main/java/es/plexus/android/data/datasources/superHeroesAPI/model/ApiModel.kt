package es.plexus.android.data.datasources.superHeroesAPI.model

import com.squareup.moshi.Json


const val NO_NETWORK_CODE = 500
const val UNKNOWN_CODE = -1
const val DEFAULT_MESSAGE: String = ""
const val UNKNOWN_ERROR_MSG = "Unknown Error"
const val NO_NETWORK_MSG = "No network available"
const val NO_DATA_MSG = "No data available"

sealed class FailureApi(val message: String, val code: Int) {

    class Unauthorized(message: String = DEFAULT_MESSAGE, code: Int = UNKNOWN_CODE) :
        FailureApi(message, code)

    class Request(message: String, code: Int) : FailureApi(message, code)
    object NoNetwork : FailureApi(NO_NETWORK_MSG, NO_NETWORK_CODE)
    object Unknown : FailureApi(UNKNOWN_ERROR_MSG, UNKNOWN_CODE)
    object NoData : FailureApi(NO_DATA_MSG, UNKNOWN_CODE)

}

data class SuperHerosApi(
    @Json(name = "data") val data: SuperHerosDataApi
)

data class SuperHerosDataApi(
    @Json(name = "offset") val offset: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "count") val count: Int,
    @Json(name = "results") val results: List<SuperHeroApi>
)

data class SuperHeroApi(

    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "thumbnail") val thumbnail: ThumbnailApi,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "comics") val comics: ComicsApi,
    @Json(name = "series") val series: SeriesApi,
    @Json(name = "stories") val stories: StoriesApi,
    @Json(name = "events") val events: EventsApi,
    @Json(name = "urls") val urls: List<UrlsApi>
)

data class ThumbnailApi(

    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)

data class SeriesApi(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsApi>,
    @Json(name = "returned") val returned: Int
)

data class ItemsApi(

    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String
)

data class ComicsApi(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsApi>,
    @Json(name = "returned") val returned: Int
)

data class UrlsApi(

    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
)

data class StoriesApi(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsApi>,
    @Json(name = "returned") val returned: Int
)

data class EventsApi(

    @Json(name = "available") val available: Int,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ItemsApi>,
    @Json(name = "returned") val returned: Int
)