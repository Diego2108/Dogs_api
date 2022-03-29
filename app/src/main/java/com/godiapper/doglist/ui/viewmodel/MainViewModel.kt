package com.godiapper.doglist.ui.viewmodel

import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godiapper.doglist.adapters.DogsAdapter
import com.godiapper.doglist.core.DogsResponse
import com.godiapper.doglist.network.DogServices
import com.godiapper.doglist.network.RetrofitInstance
import com.godiapper.doglist.repository.RespositoryDogsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val dogsResponse = MutableLiveData<DogsResponse>()

    val repositoryDogsList = RespositoryDogsList()

    val error = MutableLiveData<String>()


    fun searchByName(query:String) {
        viewModelScope.launch {
            val call2 = repositoryDogsList.getDogsByBreeds(query)
            if (
                call2.isSuccessful
            ){
                dogsResponse.postValue(call2.body())
            }else{
                error.postValue(call2.message())
            }
        }
    }


}