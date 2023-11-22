package com.example.diplom2

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.TextView
import com.example.diplom2.screens.main.MainFragment
import com.example.diplom2.screens.physic_data.NewPhysics
import com.example.diplom2.screens.physical.PhysicalFragment
import java.io.File
import java.io.IOException
import java.lang.String.format
import java.util.*

public class Calculator(){

    var kalorazh:Float = 0.0f
    var belok:Int = 0
    var gir:Int = 0
    var uglevod:Int = 0

    fun Calculate(rost: Int, ves: Int, pol:Int, vozrast:Int): IntArray {
        if (pol == 0){
            //ДЛЯ МЖУЧИН
            kalorazh = ((ves*10 + rost*6.25 - vozrast*5+5)*1.2*1.15).toFloat()
        }
        else{
            //ДЛЯ ЖЕНЩИН
            kalorazh = ((ves*10 + rost*6.25 - vozrast*5-161)*1.2).toFloat()
        }
        belok = (1.5 * ves).toInt()
        gir = ves
        uglevod = 2 * ves

        val KBGU : IntArray = intArrayOf(kalorazh.toInt(),belok,gir,uglevod)

        return KBGU
    }

}