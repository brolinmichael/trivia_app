package com.example.triviaapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
 data class Answers(@PrimaryKey(autoGenerate = true)
                     var id: Int,
                    @ColumnInfo(name = "name") var name: String,
                    @ColumnInfo(name = "cricketer") var cricketer: String,
                    @ColumnInfo(name = "color") var color: String,
                    @ColumnInfo(name = "date") var date: String){
}