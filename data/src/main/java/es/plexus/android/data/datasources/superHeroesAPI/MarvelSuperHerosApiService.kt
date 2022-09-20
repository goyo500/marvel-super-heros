package es.plexus.android.data.datasources.superHeroesAPI


import es.plexus.android.data.datasources.superHeroesAPI.model.SuperHerosApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelSuperHerosApiService {

    @GET("characters")
    suspend fun getSuperHeroesList(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 50
    ): Response<SuperHerosApi>

    @GET("characters/{characterId}")
    suspend fun getSuperHeroDetail(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<SuperHerosApi>
}