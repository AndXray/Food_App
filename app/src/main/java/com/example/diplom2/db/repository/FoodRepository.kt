package com.example.diplom2.db.repository

import androidx.lifecycle.LiveData
import com.example.diplom2.model.ExistFoodModel
import com.example.diplom2.model.FoodModel

interface FoodRepository {
    val allFoods: LiveData<List<FoodModel>>
    suspend fun insertFood(foodModel: FoodModel, onSucces:() -> Unit)
    suspend fun deleteFood(foodModel: FoodModel, onSucces:() -> Unit)
    val kalSum: Double?
    val belkiSum: Double?
    val jiriSum: Double?
    val uglevSum: Double?
    fun clearAll()
    val existFoods: LiveData<List<ExistFoodModel>>
    suspend fun insertExistFood(existFoodModel: ExistFoodModel, onSucces: () -> Unit)
    suspend fun deleteExistFood(existFoodModel: ExistFoodModel, onSucces: () -> Unit)


//    suspend fun deleteAll(foodModel: FoodModel, onSucces:() -> Unit)
}