package com.example.diplom2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.diplom2.databinding.ActivityMainBinding
import com.example.diplom2.db.FoodDataBase
import com.example.diplom2.screens.food.AllFoodViewModel
import com.example.diplom2.screens.main.MainFragment
import java.io.File
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var db: FoodDataBase
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        //this.getSupportActionBar()?.hide();
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.itemIconTintList = null

        //ПРОВЕРКА ФАЙЛА С ДАТОЙ
        val storeDirectory = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file = File(storeDirectory,"Date.txt")
        if(!file.exists()) {
            file.writeText("")
        }
        val storeDir = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val fileScore = File(storeDir,"score.txt")
        if(!fileScore.exists()) {
            fileScore.writeText("0")
    }

    }

    override fun onStart(){
        super.onStart()
        val viewModel = ViewModelProvider(this).get(AllFoodViewModel::class.java)
        val last = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()

        val storeDirectory = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file = File(storeDirectory,"Date.txt")
        val fixDate = file.readText()

        if (last != fixDate){
            viewModel.deleteAll()
            file.writeText(last)
        }
    }

    override fun onStop() {
        super.onStop()
        val last = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        val storeDirectory = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file = File(storeDirectory,"Date.txt")
        file.writeText(last)
        val date = file.readText()
    }
    fun hide(){
        binding.bottomNavView.visibility = View.GONE
    }


}