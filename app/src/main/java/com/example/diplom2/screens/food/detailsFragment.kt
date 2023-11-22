package com.example.diplom2.screens.food

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.diplom2.APP
import com.example.diplom2.R
import com.example.diplom2.databinding.FragmentAddFoodBinding
import com.example.diplom2.databinding.FragmentDetailsBinding
import com.example.diplom2.model.FoodModel
import kotlinx.android.synthetic.main.fragment_details.*
import java.lang.String.format
import kotlin.math.roundToInt

class detailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var currentFood: FoodModel
    lateinit var text:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentFood = arguments?.getSerializable("food") as FoodModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        btnBack()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding.titleDetails.text = currentFood.title
        var a: Int = currentFood.kalorazh.toInt()
        binding.kaloriiDetails.text = a.toString()
        var b: Int = currentFood.belki.toInt()
        binding.belkiDetails.text = b.toString()
        var c: Int = currentFood.jiri.toInt()
        binding.jiriDetails.text = c.toString()
        var d: Int = currentFood.uglevodi.toInt()
        binding.uglevodiDetails.text = d.toString()
        binding.vesDetails.text = currentFood.ves.toString()

        binding.btnDelete.setOnClickListener{
        viewModel.delete(currentFood){}
            APP.navController.navigate(R.id.action_detailsFragment_to_allFood)
        }
    }
    private fun btnBack(){
        binding.btnBackDetails.setOnClickListener{
            APP.navController.navigate(R.id.action_detailsFragment_to_allFood)
        }}

}