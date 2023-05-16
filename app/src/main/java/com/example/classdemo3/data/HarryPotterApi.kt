package com.example.classdemo3.data

import com.example.classdemo3.model_.HarryPotterCharactersItem
import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {
    @GET(value = "/api/characters")
    suspend fun getCharacters(): Response<List<HarryPotterCharactersItem>>
}
