package com.example.diplom2.screens.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.diplom2.R
import com.example.diplom2.REPOSITORY
import com.example.diplom2.db.FoodDataBase
import com.example.diplom2.db.repository.FoodRealisation
import com.example.diplom2.model.FoodModel
import com.example.diplom2.screens.food.AllFoodViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScrViewModel(application: Application):AndroidViewModel(application) {

    val context = application
    lateinit var navController: NavController

    fun initDataBase(){
        val daoFood = FoodDataBase.getInstance(context).getFoodDao()
        REPOSITORY = FoodRealisation(daoFood)
    }

    fun sumKalorii():Double? {
        return REPOSITORY.kalSum
        }

    fun sumBelki():Double? {
        return REPOSITORY.belkiSum
    }

    fun sumJiri():Double? {
        return REPOSITORY.jiriSum
    }

    fun sumUglevodi():Double? {
        return REPOSITORY.uglevSum
    }


//    @SuppressLint("SetTextI18n")
//    fun dayKallorii(eaten: Int, kaloryi: Int, progressBar: ProgressBar,
//                    mojno: TextView, roundCal: TextView){
//        val i = eaten
//        val newKalorazh = (kaloryi - i).toString()
//        mojno.text = "Можно ещё $newKalorazh калорий"
//        roundCal.text = eaten.toString()
//        val kaloriiProcent = ((100 * eaten) / kaloryi).toInt()
//        progressBar.setProgress(kaloriiProcent)
//    }
//    @SuppressLint("SetTextI18n")
//    fun dayBelki(eatenBelki:Int, belki:Int,
//                 belkiProgress:TextView,progressBarBelki:ProgressBar){
//
//        belkiProgress.text = eatenBelki.toString()
//        val belkiProcent = ((100 * eatenBelki) / belki).toInt()
//        progressBarBelki.setProgress(belkiProcent)
//    }
//    @SuppressLint("SetTextI18n")
//    fun dayJiri(eatenJiri: Int, jiri:Int,
//                jiriProgress: TextView,progressBarJiri: ProgressBar){
//        jiriProgress.text = eatenJiri.toString()
//        val jiriProcent = (100 * eatenJiri) / jiri
//        progressBarJiri.setProgress(jiriProcent)
//    }
//    @SuppressLint("SetTextI18n")
//    fun ddayUglevodi(eatenUglevodi: Int, uglevody:Int,
//                     uglevodiProgress: TextView,progressBarUglevodi: ProgressBar){
//        uglevodiProgress.text = eatenUglevodi.toString()
//        val uglevodiProcent = (100 * eatenUglevodi) / uglevody
//        progressBarUglevodi.setProgress(uglevodiProcent)
//    }

}