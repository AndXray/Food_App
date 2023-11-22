package com.example.diplom2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplom2.db.dao.FoodDao
import com.example.diplom2.model.FoodModel

@Database(entities = [FoodModel::class], version = 1)
abstract class FoodDataBase: RoomDatabase() {

    abstract fun getFoodDao(): FoodDao


    companion object{
        private var database: FoodDataBase ?= null

        @Synchronized
        fun getInstance(context: Context): FoodDataBase{
            return if (database == null){

                database = Room.databaseBuilder(context,FoodDataBase::class.java,"db").build()
                database as FoodDataBase

            } else{
                database as FoodDataBase
            }
        }

    }



}