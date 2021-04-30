package com.example.triviaapp.activities

import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.adapters.HistoryAdapter
import com.example.triviaapp.database.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var rvHistory : RecyclerView? = null
    private var toolBar:Toolbar? = null

    /**
     * 1) Initialising the views
     * 2) Setting up support action bar
     * 3) Enabling back button in toolbar
     * 4) Setting title for toolbar
     * 5) Setting toolbar text color
     * 6) Setting up toolbar navigation click listener
     * 7) Setting up thread for performing database operation, this is done inorder to avoid database operations in UI thread
     * 8) Declaring the instance of database
     * 9) Fetching the history of answers from database to a list
     * 10) Setting up history adapter and recyclerview for listing previous history of games taken
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        rvHistory = findViewById(R.id.rvHistory) // Step 1
        toolBar = findViewById(R.id.toolbar)

        setSupportActionBar(toolBar) // Step 2
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Step 3
        supportActionBar?.title = "History" // Step 4

        toolBar?.setTitleTextColor(resources.getColor(R.color.white)) // Step 5
        toolBar?.setNavigationOnClickListener { // Step 6
            onBackPressed()
        }


        Thread{ // Step 7
            val db = AppDatabase(this@HistoryActivity) // Step 8
            val answers = db.AnswersDao().getAll() // Step 9
            if (answers.isNotEmpty()){
                val historyAdapter = HistoryAdapter(this@HistoryActivity,answers) // Step 10
                rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                rvHistory?.adapter = historyAdapter
            }else{
                runOnUiThread(Runnable { Toast.makeText(applicationContext,"No history found",Toast.LENGTH_SHORT).show() })
            }
        }.start()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
}