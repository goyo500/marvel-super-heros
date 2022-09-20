package es.plexus.android.data.datasources.superHeroesDB.mappers

import es.plexus.android.data.datasources.superHeroesDB.model.HeroDetailEntity
import es.plexus.android.data.datasources.superHeroesDB.model.HeroEntity
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.domain.model.SuperHeroList


internal fun List<HeroEntity>.toDomain(): SuperHeroList {
    val results = arrayListOf<SuperHero>()
    this.forEach { hero ->
        results.add(
            SuperHero(
                id = hero.heroId,
                name = hero.heroName,
                description = hero.heroDescription,
                picture = hero.heroPicture
            )
        )
    }
    return SuperHeroList(results)
}

internal fun HeroDetailEntity.toDomain(): SuperHero =
    SuperHero(
        id = this.heroId,
        name = this.heroName,
        checkHeroDescription(this.heroDescription),
        modified = this.lastUpdate,
        picture = this.heroPicture,
        comicsNumber = this.comicsNumber,
        seriesNumber = this.seriesNumber,
        storiesNumber = this.storiesNumber,
        eventsNumber = this.eventsNumber
    )

private fun checkHeroDescription(description: String): String =
    if (description.isEmpty() or description.isBlank())
        "Shield Database has no data for this hero"
    else
        description