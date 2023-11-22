package com.example.diplom2.screens.physical

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.diplom2.APP
import com.example.diplom2.R
import com.example.diplom2.Calculator
import com.example.diplom2.databinding.FragmentDetailsBinding
import com.example.diplom2.databinding.FragmentPhysicalBinding
import com.example.diplom2.screens.main.MainScrViewModel

import java.io.File
import java.io.IOException
import java.util.*

class PhysicalFragment : Fragment() {
    lateinit var binding: FragmentPhysicalBinding

    var ves:Int = 0;
    var rost:Int = 0;
    var pol = 0;
    var vozrast = 0;
    lateinit var Pol:TextView
    lateinit var Ves:TextView
    lateinit var Rost:TextView
    lateinit var Vozrast:TextView
    lateinit var obmen: TextView
    lateinit var Belki: TextView
    lateinit var Jiri: TextView
    lateinit var Uglevodi: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhysicalBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(PhysicalViewModel::class.java)
        Rost = view.findViewById(R.id.rost)
        Ves = view.findViewById(R.id.ves)
        Vozrast = view.findViewById(R.id.vozrast)
        Pol = view.findViewById(R.id.pol)
        obmen = view.findViewById(R.id.obmen)
        Belki = view.findViewById(R.id.belki)
        Jiri = view.findViewById(R.id.jiri)
        Uglevodi = view.findViewById(R.id.uglevodi)

        val storeDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file = File(storeDirectory,"user.txt")
        if(file.exists()) {
            var i = 0
            try{
                val sc = Scanner(file)
                while (sc.hasNextLine()){
                    val line = sc.nextLine()
                    if(i==0)
                        {vozrast = line.toInt()
                        Vozrast.text = line}
                    if (i==1)
                        {rost = line.toInt()
                        Rost.text = line}
                    if (i==2)
                        {ves = line.toInt()
                        Ves.text = line}
                    if (i==3)
                    {   if (line=="Мужской") {
                        pol = 0
                        Pol.text = line}
                        else{ pol = 1
                        Pol.text = line}
                        }

                    i++
                }
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        var Kalorazh = Calculator().Calculate(rost,ves,pol,vozrast)
        obmen.text = Kalorazh[0].toString()
        Belki.text = Kalorazh[1].toString()
        Jiri.text = Kalorazh[2].toString()
        Uglevodi.text = Kalorazh[3].toString()

        binding.btnNewData.setOnClickListener{
            APP.navController.navigate(R.id.action_physical_to_newPhysics)
        }



    }
}

