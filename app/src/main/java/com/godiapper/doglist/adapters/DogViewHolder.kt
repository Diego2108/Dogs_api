package com.godiapper.doglist.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.godiapper.doglist.databinding.ItemDogsBinding
import com.squareup.picasso.Picasso

class DogViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemDogsBinding.bind(view)

    fun bind(image:String){
        Picasso.get().load(image).into(binding.imageviewDog)
        binding.imageviewDog
    }
}