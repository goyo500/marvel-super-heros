package es.plexus.android.data.datasources.superHeroesDB.mappers

import es.plexus.android.data.datasources.superHeroesDB.model.HeroDetailEntity
import es.plexus.android.data.datasources.superHeroesDB.model.HeroEntity
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList


internal fun SuperHeroList.toEntityList(): ArrayList<HeroEntity> {
    val entities = arrayListOf<HeroEntity>()

    this.results.forEach { result ->
        entities.add(
            HeroEntity(
                result.id,
                result.name,
                result.description,
                result.picture
            )
        )
    }
    return entities
}

internal fun SuperHero.toEntity(): HeroDetailEntity =
    HeroDetailEntity(
        this.id,
        this.name,
        this.description,
        this.seriesNumber,
        this.comicsNumber,
        this.storiesNumber,
        this.eventsNumber,
        this.picture,
        this.modified
    )

