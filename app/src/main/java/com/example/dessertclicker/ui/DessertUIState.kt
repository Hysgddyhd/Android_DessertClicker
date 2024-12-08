package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.dessertclicker.model.Dessert
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUIState (
     var revenue : Int =0,
    var dessertsSold : Int = 0,

    val currentDessertIndex : Int = 0,

    val currentDessertPrice : Int = dessertList[currentDessertIndex].price,
    @DrawableRes
     val currentDessertImageId : Int = dessertList[currentDessertIndex].imageId,
)

