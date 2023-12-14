package com.example.diplom2.screens.food.addfood

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom2.REPOSITORY
import com.example.diplom2.databinding.ActivityMainBinding
import com.example.diplom2.model.ExistFoodModel
import com.example.diplom2.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFoodViewModel:ViewModel() {
    fun insert(foodModel: FoodModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY.insertFood(foodModel) {
                onSuccess()
            }
        }
    fun insertExistFood(existFoodModel: ExistFoodModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY.insertExistFood(existFoodModel) {
                onSuccess()
            }
        }
    fun calculateFood(
        eaten: Int,
        kaloryi: Int,
        progressBar: ProgressBar,
        mojno: TextView,
        roundCal: TextView,
        eatenBelki: Int,
        eatenJiri: Int,
        progressBarBelki: ProgressBar,
        progressBarJiri: ProgressBar,
        belkiProgress: TextView,
        jiriProgress: TextView,
        belki: Int,
        jiri: Int,
        eatenUglevodi: Int,
        uglevody: Int,
        uglevodiProgress: TextView,
        progressBarUglevodi: ProgressBar
    ) {
        dayKallorii(eaten, kaloryi, progressBar, mojno, roundCal)
        dayBelki(eatenBelki, belki, belkiProgress, progressBarBelki)
        dayJiri(eatenJiri, jiri, jiriProgress, progressBarJiri)
        ddayUglevodi(eatenUglevodi, uglevody, uglevodiProgress, progressBarUglevodi)
    }
    fun dayKallorii(eaten: Int, kaloryi: Int, progressBar: ProgressBar,
                    mojno: TextView, roundCal: TextView
    ){
        val i = eaten
        val newKalorazh = (kaloryi - i).toString()
        mojno.text = "Можно ещё $newKalorazh калорий"
        roundCal.text = eaten.toString()
        val kaloriiProcent = ((100 * eaten) / kaloryi).toInt()
        progressBar.setProgress(kaloriiProcent)
    }
    fun dayBelki(eatenBelki:Int, belki:Int,
                 belkiProgress: TextView, progressBarBelki: ProgressBar
    ){
        belkiProgress.text = eatenBelki.toString()
        val belkiProcent = ((100 * eatenBelki) / belki).toInt()
        progressBarBelki.setProgress(belkiProcent)
    }
    fun dayJiri(eatenJiri: Int, jiri:Int,
                jiriProgress: TextView, progressBarJiri: ProgressBar
    ){
        jiriProgress.text = eatenJiri.toString()
        val jiriProcent = (100 * eatenJiri) / jiri
        progressBarJiri.setProgress(jiriProcent)
    }
    fun ddayUglevodi(eatenUglevodi: Int, uglevody:Int,
                     uglevodiProgress: TextView, progressBarUglevodi: ProgressBar
    ){
        uglevodiProgress.text = eatenUglevodi.toString()
        val uglevodiProcent = (100 * eatenUglevodi) / uglevody
        progressBarUglevodi.setProgress(uglevodiProcent)

    }

}