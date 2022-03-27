package com.godiapper.doglist.network

import com.godiapper.doglist.core.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogServices {
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogsResponse>
}