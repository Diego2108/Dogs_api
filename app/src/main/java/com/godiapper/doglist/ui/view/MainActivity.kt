package com.godiapper.doglist.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.godiapper.doglist.adapters.DogsAdapter
import com.godiapper.doglist.core.DogsResponse
import com.godiapper.doglist.databinding.ActivityMainBinding
import com.godiapper.doglist.network.DogServices
import com.godiapper.doglist.network.RetrofitInstance
import com.godiapper.doglist.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private  val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: DogsAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.svDogs.setOnQueryTextListener(this)
        setContentView(binding.root)
        initObservers()
    }


    private fun initObservers(){
        viewModel.dogsResponse.observe(this){ img->
            with(binding.rvDogs){
                adapter = DogsAdapter(img.images)
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            viewModel.searchByName(query.toLowerCase())
        }
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}