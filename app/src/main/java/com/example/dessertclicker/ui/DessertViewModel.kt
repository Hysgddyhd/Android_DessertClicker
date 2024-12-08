package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.properties.Delegates

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUIState())
    val uiState : StateFlow<DessertUIState> = _uiState.asStateFlow()



    fun onDessertClicked() {
       //  revenue += currentDessertPrice
         //       dessertsSold++

                // Show the next dessert
                val dessertNextIndex = determineDessertToShow()
        _uiState.update { currentState ->
                currentState.copy(
                    revenue = currentState.revenue + currentState.currentDessertPrice ,
                    dessertsSold = currentState.dessertsSold.inc(),
                    currentDessertImageId = dessertList[dessertNextIndex].imageId,
                    currentDessertPrice = dessertList[dessertNextIndex].price,

                )

        }
    }

/**
 * Determine which dessert to show.
 */
fun determineDessertToShow(
        dessertsSold: Int = uiState.value.dessertsSold + 1
): Int {
    var dessertIndex = 0
    for (index in dessertList.indices) {
        if (dessertsSold >= dessertList[index].startProductionAmount) {
            dessertIndex = index
        } else {
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            break
        }
    }

    return dessertIndex
}
}