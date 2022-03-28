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
    val call = MutableLiveData<Boolean>()

    val repositoryDogsList = RespositoryDogsList()

    private lateinit var adapter: DogsAdapter
    private val dogImages = mutableListOf<String>()


    fun onCreate(){
        viewModelScope.launch {
            call.postValue(true)
            val result = repositoryDogsList
            /*if (!result.isNullOrEmpty()){
                dogsResponse.postValue(result[])

            }*/
        }
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitInstance.getRetrofit().create(DogServices::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
                hideKeyboard()
            }

        }

    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
    private fun hideKeyboard() {
        val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}