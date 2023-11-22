package com.example.diplom2.screens.physic_data

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isNotEmpty
import androidx.lifecycle.ViewModelProvider
import com.example.diplom2.APP
import com.example.diplom2.MainActivity
import com.example.diplom2.R
import com.example.diplom2.databinding.FragmentAddFoodBinding
import com.example.diplom2.databinding.FragmentNewPhysicsBinding
import com.example.diplom2.databinding.FragmentPhysicalBinding
import com.example.diplom2.model.FoodModel
import com.example.diplom2.screens.food.addfood.AddFoodViewModel
import kotlinx.android.synthetic.main.fragment_new_physics.*
import kotlinx.android.synthetic.main.fragment_physical.*
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.roundToInt

class NewPhysics : Fragment() {
    lateinit var binding: FragmentNewPhysicsBinding
    lateinit var spinner: Spinner
    lateinit var gender: String
    val Pol : Array<String> = arrayOf("Мужской","Женский")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPhysicsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radio()
        init()
        btnBack()
    }

    fun init(){
        val viewModel = ViewModelProvider(this).get(NewPhysicViewModel::class.java)
        spinner = requireView().findViewById(R.id.title_pol)
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.pol,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.onItemSelectedListener = this
                gender = Pol[p2]

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.btnAddNewPhys.setOnClickListener {
            val rosttext = binding.titleRost.text.toString()
            val vestext = binding.titleVes.text.toString()
            val vozrasttext = binding.titleVozrast.text.toString()
            val pol = binding.titlePol.toString()
        if (rosttext.isNotEmpty() && vestext.isNotEmpty() && vozrasttext.isNotEmpty() && pol.isNotEmpty()) {

            val storeDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
            val file = File(storeDirectory,"user.txt")
            if(!file.exists()) {
                file.writeText("")
            }

            file.writeText("$vozrasttext\n$rosttext\n$vestext\n$gender")
            APP.navController.navigate(R.id.action_newPhysics_to_physical)
        }
        else{
            Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
    }
    fun radio(){
        val storeDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val fileScore = File(storeDirectory,"score.txt")
        radioButtonDown.setOnClickListener{
            fileScore.writeText("1")
        }
        radioButtonUp.setOnClickListener{
            fileScore.writeText("2")
        }
    }
    private fun btnBack(){
        binding.btnBackPhys.setOnClickListener{
            APP.navController.navigate(R.id.action_newPhysics_to_physical)
        }}

}