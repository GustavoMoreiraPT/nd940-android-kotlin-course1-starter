package com.udacity.shoestore.screens.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListingViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = seedShoes()
    }

    fun add(shoe: Shoe){
        val newInventory = mutableListOf<Shoe>()
        newInventory.addAll(_shoes.value!!.asIterable())
        newInventory.add(shoe)
        _shoes.value = newInventory
    }

    private fun seedShoes(): List<Shoe> {
        val shoes = mutableListOf<Shoe>()
        for (i in 1..10){
            shoes.add(Shoe("Shoe$i", 37.0 + i, "Company$i", "This shoe is called Shoe$i" ))
        }

        return shoes
    }
}