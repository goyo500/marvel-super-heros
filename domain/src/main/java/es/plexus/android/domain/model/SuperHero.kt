package es.plexus.android.domain.model


data class SuperHero(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String = DEFAULT_STRING,
    val picture: String = DEFAULT_STRING,
    val resourceURI: String = DEFAULT_STRING,
    val comicsNumber: String = DEFAULT_STRING,
    val seriesNumber: String = DEFAULT_STRING,
    val storiesNumber: String = DEFAULT_STRING,
    val eventsNumber: String = DEFAULT_STRING,
    val urls: List<UrlsBo> = emptyList()
)


data class SuperHeroList(val results: List<SuperHero>)


data class UrlsBo(
    val type: String = DEFAULT_STRING,
    val url: String = DEFAULT_STRING
)
