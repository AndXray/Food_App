package com.example.diplom2.screens.main

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.diplom2.Calculator
import com.example.diplom2.R
import com.example.diplom2.databinding.FragmentMainBinding
import java.io.File
import java.io.IOException
import java.util.*


class MainFragment : Fragment() {
    var ves = 70;
    var rost: Int = 180;
    var pol = 0;
    var vozrast = 20;
    lateinit var progressBar: ProgressBar
    lateinit var progressBarBelki: ProgressBar
    lateinit var progressBarJiri: ProgressBar
    lateinit var progressBarUglevodi: ProgressBar
    lateinit var mojno: TextView
    lateinit var roundCal: TextView
    lateinit var belkiProgress: TextView
    lateinit var jiriProgress: TextView
    lateinit var uglevodiProgress: TextView
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val storeDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val file = File(storeDirectory,"user.txt")
        if(file.exists()) {
            var i = 0
            try{
                val sc = Scanner(file)
                while (sc.hasNextLine()){
                    val line = sc.nextLine()
                    if(i==0)
                    vozrast = line.toInt()
                    if (i==1)
                    rost = line.toInt()
                    if (i==2)
                    ves = line.toInt()
                    if (i==3)
                    { if (line=="Мужской") pol = 0
                    else pol = 1
                    }
                    i++
                }
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        val viewModel = ViewModelProvider(this)
            .get(MainScrViewModel::class.java)
//        val Kalorazh = Calculator().Calculate(rost, ves, pol, vozrast)
//        var kaloryi = Kalorazh[0]
//        var belki = Kalorazh[1]
//        var jiri = Kalorazh[2]
//        var uglevody = Kalorazh[3]
        viewModel.initDataBase()

        calculate()

//        Thread {
//            val eaten = (viewModel.sumKalorii() ?: 0.0).toInt()
//            val eatenBelki = (viewModel.sumBelki() ?: 0.0).toInt()
//            val eatenJiri = (viewModel.sumJiri() ?: 0.0).toInt()
//            val eatenUglevodi = (viewModel.sumUglevodi() ?: 0.0).toInt()
//            progress()
//            viewModel.dayKallorii(eaten, kaloryi, progressBar, mojno, roundCal)
//            viewModel.dayBelki(eatenBelki, belki, belkiProgress, progressBarBelki)
//            viewModel.dayJiri(eatenJiri, jiri, jiriProgress, progressBarJiri)
//            viewModel.ddayUglevodi(eatenUglevodi, uglevody, uglevodiProgress, progressBarUglevodi)
//        }.start()
    }


    fun progress()
    {
        progressBar = requireView().findViewById(R.id.progress_bar_calorii)
        roundCal = requireView().findViewById(R.id.progress_text_calorii)
        mojno = requireView().findViewById(R.id.mojno)
        belkiProgress = requireView().findViewById(R.id.progress_text_belki)
        progressBarBelki = requireView().findViewById(R.id.progress_bar_belki)
        jiriProgress = requireView().findViewById(R.id.progress_text_giri)
        progressBarJiri = requireView().findViewById(R.id.progress_bar_giri)
        uglevodiProgress = requireView().findViewById(R.id.progress_text_uglevodi)
        progressBarUglevodi = requireView().findViewById(R.id.progress_bar_uglevodi)
    }
    fun calculate(){
        val storeDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val fileScore = File(storeDirectory,"score.txt")
        if (!fileScore.exists())
            fileScore.writeText("0")
        val j = fileScore.readText().toInt()

        val Kalorazh = Calculator().Calculate(rost, ves, pol, vozrast)
        var kaloryi = Kalorazh[0]
        val belki = Kalorazh[1]
        val jiri = Kalorazh[2]
        val uglevody = Kalorazh[3]

        /////////////////////
        if(j == 2)
            kaloryi = (kaloryi*1.15).toInt()
        if(j == 1)
            kaloryi = (kaloryi-kaloryi*0.15).toInt()
        ////////////////////

        val viewModel = ViewModelProvider(this)
            .get(MainScrViewModel::class.java)
        Thread {
            val eaten = (viewModel.sumKalorii() ?: 0.0).toInt()
            val eatenBelki = (viewModel.sumBelki() ?: 0.0).toInt()
            val eatenJiri = (viewModel.sumJiri() ?: 0.0).toInt()
            val eatenUglevodi = (viewModel.sumUglevodi() ?: 0.0).toInt()
        progress()
            getActivity()?.runOnUiThread(Runnable {
                dayKallorii(eaten, kaloryi, progressBar, mojno, roundCal)
                dayBelki(eatenBelki, belki, belkiProgress, progressBarBelki)
                dayJiri(eatenJiri, jiri, jiriProgress, progressBarJiri)
                ddayUglevodi(eatenUglevodi, uglevody, uglevodiProgress, progressBarUglevodi)
            })

        }.start()
    }
    fun dayKallorii(eaten: Int, kaloryi: Int, progressBar: ProgressBar,
                    mojno: TextView, roundCal: TextView){
        val i = eaten
        val newKalorazh = ((kaloryi - i)).toString()
        mojno.text = "Можно ещё $newKalorazh калорий"
        roundCal.text = eaten.toString()
        val kaloriiProcent = ((100 * eaten) / kaloryi).toInt()
        progressBar.setProgress(kaloriiProcent)
    }
    fun dayBelki(eatenBelki:Int, belki:Int,
                 belkiProgress:TextView,progressBarBelki:ProgressBar){
            belkiProgress.text = eatenBelki.toString()
            val belkiProcent = ((100 * eatenBelki) / belki)
            progressBarBelki.setProgress(belkiProcent)
    }
    fun dayJiri(eatenJiri: Int, jiri:Int,
                jiriProgress: TextView,progressBarJiri: ProgressBar){
            jiriProgress.text = eatenJiri.toString()
            val jiriProcent = ((100 * eatenJiri) / jiri).toInt()
            progressBarJiri.setProgress(jiriProcent)
    }
    fun ddayUglevodi(eatenUglevodi: Int, uglevody:Int,
                     uglevodiProgress: TextView,progressBarUglevodi: ProgressBar){
            uglevodiProgress.text = eatenUglevodi.toString()
            val uglevodiProcent = ((100 * eatenUglevodi) / uglevody)
            progressBarUglevodi.setProgress(uglevodiProcent)

    }


}
