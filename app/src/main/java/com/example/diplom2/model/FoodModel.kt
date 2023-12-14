package com.example.diplom2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_food")
class FoodModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var title: String ="",

    @ColumnInfo
    var kalorazh: Double,

    @ColumnInfo
    var belki: Double,

    @ColumnInfo
    var jiri: Double,

    @ColumnInfo
    var uglevodi: Double,

    @ColumnInfo
    var ves: Int
        ) : Serializable

//@Entity(tableName = "table_exist_food")
//class ExistFoodModel(
//    @PrimaryKey(autoGenerate = false)
//    var id: Int = 0,
//
//    @ColumnInfo
//    var title: String ="",
//
//    @ColumnInfo
//    var kalorazh: Double,
//
//    @ColumnInfo
//    var belki: Double,
//
//    @ColumnInfo
//    var jiri: Double,
//
//    @ColumnInfo
//    var uglevodi: Double,
//
//    @ColumnInfo
//    var ves: Int
//    ) : Serializable
