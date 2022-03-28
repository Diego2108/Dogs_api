package com.godiapper.doglist.network

import com.godiapper.doglist.core.DogsResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DogServices {
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogsResponse>
}

