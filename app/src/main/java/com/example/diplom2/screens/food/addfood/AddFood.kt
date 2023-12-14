package com.example.diplom2.screens.food.addfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.diplom2.APP
import com.example.diplom2.MainActivity
import com.example.diplom2.R
import com.example.diplom2.databinding.FragmentAddFoodBinding
import com.example.diplom2.model.ExistFoodModel
import com.example.diplom2.model.FoodModel
import com.example.diplom2.screens.main.MainFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_add_food.*
import java.util.logging.Logger
import kotlin.math.roundToInt

class AddFood : Fragment() {
    lateinit var binding: FragmentAddFoodBinding
    private var gson: Gson? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFoodBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //MainActivity().hide()
        init()
        btnBack()
        //AddFoodViewModel().hideMenu()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddFoodViewModel::class.java)

        binding.btnAddNewFood.setOnClickListener {

                val gramFood = binding.vesFood.text.toString()
                val titleFood = binding.titleFood.text.toString()
                val kaloriiFood = binding.kaloriiFood.text.toString()
                val belkiFood = binding.belkiFood.text.toString()
                val jiriFood = binding.jiriFood.text.toString()
                val uglevodiFood = binding.uglevodiFood.text.toString()



            if (titleFood.isNotEmpty() && kaloriiFood.isNotEmpty() && belkiFood.isNotEmpty() &&
                jiriFood.isNotEmpty() && uglevodiFood.isNotEmpty() && gramFood.isNotEmpty()) {

                val gram = gramFood.toFloat()/100

                viewModel.insert(
                    FoodModel(
                        title = titleFood, kalorazh = (kaloriiFood.toFloat()*gram).roundToInt().toDouble(),
                        belki = (belkiFood.toFloat()*gram).roundToInt().toDouble(),
                        jiri = (jiriFood.toFloat()*gram).roundToInt().toDouble(),
                        uglevodi = (uglevodiFood.toFloat()*gram).roundToInt().toDouble(),
                        ves = gramFood.toInt()
                    )
                )
                {}
                viewModel.insertExistFood(
                    ExistFoodModel(
                        title = titleFood, kalorazh = (kaloriiFood.toFloat()).roundToInt().toDouble(),
                        belki = (belkiFood.toFloat()).roundToInt().toDouble(),
                        jiri = (jiriFood.toFloat()).roundToInt().toDouble(),
                        uglevodi = (uglevodiFood.toFloat()).roundToInt().toDouble(),
                        ves = 1
                    )
                )
                {}
                APP.navController.navigate(R.id.action_addFood_to_allFood)
            }
            else{
                Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
}


    }
private fun btnBack(){
    binding.btnBackAddFood.setOnClickListener{
        APP.navController.navigate(R.id.action_addFood_to_allFood)
    }
}

}