package com.example.diplom2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.diplom2.model.FoodModel

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(foodModel: FoodModel)

    @Delete
    fun delete(foodModel: FoodModel)

    @Query("SELECT * from table_food")
    fun getFoods(): LiveData<List<FoodModel>>

    @Query("SELECT SUM(kalorazh) from table_food")
    fun sumKalorii(): Double?

    @Query("SELECT SUM(belki) from table_food")
    fun sumBelki(): Double?

    @Query("SELECT SUM(jiri) from table_food")
    fun sumJiri(): Double?

    @Query("SELECT SUM(uglevodi) from table_food")
    fun sumUglevodi(): Double?

    @Query("DELETE from table_food")
    fun deleteAll()


//    @Query("DELETE from table_food")
//    fun deleteAll(foodModel: FoodModel)
}
