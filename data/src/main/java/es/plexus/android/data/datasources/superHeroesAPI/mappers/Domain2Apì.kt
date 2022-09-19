package es.plexus.android.data.datasources.superHeroesAPI.mappers

import es.plexus.android.data.datasources.superHeroesAPI.model.SuperHeroApi
import es.plexus.android.data.datasources.superHeroesAPI.model.SuperHerosApi
import es.plexus.android.data.datasources.superHeroesAPI.model.ThumbnailApi
import es.plexus.android.data.datasources.superHeroesAPI.model.UrlsApi
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList
import es.plexus.android.domain.model.UrlsBo

internal fun SuperHerosApi.toDomain(): SuperHeroList =
    SuperHeroList(this.data.results.map { it.toDomain() })

internal fun SuperHeroApi.toDomain(): SuperHero =
    SuperHero(
        this.id,
        this.name,
        this.description,
        this.modified,
        this.thumbnail.toDomain(),
        this.resourceURI,
        this.comics.available.toString(),
        this.series.available.toString(),
        this.stories.available.toString(),
        this.events.available.toString(),
        this.urls.map { it.toDomain() }
    )

internal fun ThumbnailApi.toDomain(): String = this.path + "." + this.extension

internal fun UrlsApi.toDomain(): UrlsBo =
    UrlsBo(this.type, this.url)



