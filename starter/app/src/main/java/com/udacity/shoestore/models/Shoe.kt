package com.udacity.shoestore.models

import android.os.Parcelable
import android.widget.EditText
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable{

    fun onSizeChanged(editText: EditText){
        val stringSize = editText.text.toString()
        this.size = stringSize.toDouble()
    }
}