package com.example.diplom2.db.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.diplom2.db.dao.FoodDao
import com.example.diplom2.model.ExistFoodModel
import com.example.diplom2.model.FoodModel

class FoodRealisation(private val foodDao: FoodDao): FoodRepository {

    override val allFoods: LiveData<List<FoodModel>>
        get() = foodDao.getFoods()

    override val existFoods: LiveData<List<ExistFoodModel>>
        get() = foodDao.getExistFoods()

    override suspend fun insertExistFood(existFoodModel: ExistFoodModel, onSucces: () -> Unit){
        foodDao.insertExistFood(existFoodModel)
        onSucces()
    }

    override suspend fun deleteExistFood(existFoodModel: ExistFoodModel, onSucces: () -> Unit) {
        foodDao.delete(existFoodModel)
        onSucces()
    }

    override suspend fun insertFood(foodModel: FoodModel, onSucces: () -> Unit) {
        foodDao.insert(foodModel)
        onSucces()
    }

    override suspend fun deleteFood(foodModel: FoodModel, onSucces: () -> Unit) {
        foodDao.delete(foodModel)
        onSucces()
    }

    override fun clearAll(){
        AsyncTask.execute{foodDao.deleteAll()}

    }

    override val kalSum: Double?
        get() = foodDao.sumKalorii()

    override val belkiSum: Double?
        get() = foodDao.sumBelki()

    override val jiriSum: Double?
        get() = foodDao.sumJiri()

    override val uglevSum: Double?
        get() = foodDao.sumUglevodi()
}

