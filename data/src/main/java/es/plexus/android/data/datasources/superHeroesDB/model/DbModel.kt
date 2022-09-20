package es.plexus.android.data.datasources.superHeroesDB.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = HeroEntity.TABLE_NAME)
data class HeroEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HERO_ID) @NotNull val heroId: Int,
    @ColumnInfo(name = HERO_NAME) @NotNull val heroName: String,
    @ColumnInfo(name = HERO_DESCRIPTION) @NotNull val heroDescription: String,
    @ColumnInfo(name = HERO_PICTURE) @NotNull val heroPicture: String
) {
    companion object {
        const val TABLE_NAME = "hero"
        const val HERO_ID = "id"
        const val HERO_NAME = "name"
        const val HERO_DESCRIPTION = "description"
        const val HERO_PICTURE = "picture"
    }
}

@Entity(tableName = HeroDetailEntity.TABLE_NAME)
data class HeroDetailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HERO_ID) @NotNull val heroId: Int,
    @ColumnInfo(name = HERO_NAME) @NotNull val heroName: String,
    @ColumnInfo(name = HERO_DESCRIPTION) @NotNull val heroDescription: String,
    @ColumnInfo(name = SERIES_NUMBER) @NotNull val seriesNumber: String,
    @ColumnInfo(name = COMICS_NUMBER) @NotNull val comicsNumber: String,
    @ColumnInfo(name = STORIES_NUMBER) @NotNull val storiesNumber: String,
    @ColumnInfo(name = EVENTS_NUMBER) @NotNull val eventsNumber: String,
    @ColumnInfo(name = HERO_PICTURE) @NotNull val heroPicture: String,
    @ColumnInfo(name = HERO_LAST_UPDATE) @NotNull val lastUpdate: String
) {
    companion object {
        const val TABLE_NAME = "hero_detail"
        const val HERO_ID = "id"
        const val HERO_NAME = "name"
        const val HERO_DESCRIPTION = "description"
        const val HERO_PICTURE = "picture"
        const val SERIES_NUMBER = "series_number"
        const val COMICS_NUMBER = "comics_number"
        const val STORIES_NUMBER = "stories_number"
        const val EVENTS_NUMBER = "events_number"
        const val HERO_LAST_UPDATE = "last_update"
    }
}