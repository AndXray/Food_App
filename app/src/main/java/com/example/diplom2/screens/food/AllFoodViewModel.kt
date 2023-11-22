package com.example.diplom2.screens.food

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom2.REPOSITORY
import com.example.diplom2.db.FoodDataBase
import com.example.diplom2.db.repository.FoodRealisation
import com.example.diplom2.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AllFoodViewModel(application: Application):AndroidViewModel(application) {

    val context = application

    fun initDataBase(){
        val daoFood = FoodDataBase.getInstance(context).getFoodDao()
        REPOSITORY = FoodRealisation(daoFood)
    }
    fun getAllFoods():LiveData<List<FoodModel>> {

        return REPOSITORY.allFoods
    }
    fun deleteAll(){

        REPOSITORY.clearAll()
    }


//    fun sum():LiveData<List<FoodModel>>{
//    }


//    fun deleteAll(foodModel: FoodModel, onSuccess:() -> Unit) =
//        viewModelScope.launch (Dispatchers.IO){
//            REPOSITORY.deleteAll(foodModel) {
//                onSuccess()
//
//
//            }
//        }
}
