package com.example.triviaapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnswersDao {

    @Query("SELECT * FROM answers")
    fun getAll(): List<Answers>

    @Insert
    fun insertAll(vararg answers: Answers)
}