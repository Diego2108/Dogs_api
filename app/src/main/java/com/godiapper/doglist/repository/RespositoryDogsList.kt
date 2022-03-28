package com.godiapper.doglist.repository

import android.provider.MediaStore
import com.godiapper.doglist.adapters.DogsAdapter
import com.godiapper.doglist.network.DogServices
import com.godiapper.doglist.network.RetrofitInstance
import com.godiapper.doglist.network.RetrofitInstance.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class RespositoryDogsList {
    private  val retrofit = RetrofitInstance.getRetrofit().create(DogServices::class.java)

}