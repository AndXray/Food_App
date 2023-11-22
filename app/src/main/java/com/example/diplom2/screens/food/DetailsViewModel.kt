package com.example.diplom2.screens.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom2.REPOSITORY
import com.example.diplom2.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    fun delete(foodModel: FoodModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY.deleteFood(foodModel) {
                onSuccess()
            }
        }
}