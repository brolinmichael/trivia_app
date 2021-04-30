package com.example.triviaapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.example.triviaapp.R
import com.example.triviaapp.database.Answers
import com.example.triviaapp.database.AppDatabase
import com.example.triviaapp.fragments.SummaryFragment
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    lateinit var db : AppDatabase
    var toolBar: Toolbar? = null
    private var doubleBackPressed : Boolean = false
    private val activityScope = CoroutineScope(Dispatchers.Main)


    /**
     * 1) Initialising the views
     * 2) Setting up support action bar
     * 3) Enabling back button in toolbar
     * 4) Hiding toolbar title
     * 5) Setting up toolbar navigation click listener
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        toolBar = findViewById(R.id.toolbar) // Step 1

        setSupportActionBar(toolBar) // Step 2
        supportActionBar?.setDisplayShowTitleEnabled(false) // Step 3
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Step 4

        toolBar?.setNavigationOnClickListener { // Step 5
            onBackPressed()
        }
    }

    /**
     * 1) Declaring the instance of Database
     * 2) Fetching the list of previous games taken from database
     * 3) Getting current date and time
     * 4) Formatting the current date time
     * 5) Inserting the values received from the methods constructor to database
     */
    fun insert(name:String,cricketer: String,color:String){
        db = AppDatabase(this@HomeActivity) // Step 1
        val answers = db.AnswersDao().getAll() // Step 2
        val sdf = SimpleDateFormat("dd MMMM hh:mm a", Locale.getDefault()) // Step 3
        val currentDateAndTime: String = sdf.format(Date()) // Step 4
        db.AnswersDao().insertAll(Answers(answers.size+1,name,cricketer,color,currentDateAndTime)) // Step 5
    }

    /**
     * 1) Getting the instance of the navigation host fragment from support fragment manager
     * 2) Checking whether the back stack entry of child fragment manager of navigation host fragment is greater than 0
     * 3) If current fragment is instance of Summary fragment then do nothing on back press
     * 4) If back stack entry is 0 then implementing the logic of double back press to exit application
     */
    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) // Step 1
        if (navHostFragment?.childFragmentManager?.backStackEntryCount!!>0){ // Step 2
            if (navHostFragment.childFragmentManager.primaryNavigationFragment is SummaryFragment){ // Step 3
                // Do nothing
            } else{
                super.onBackPressed()
            }
        } else{
                if (!doubleBackPressed){ // Step 4
                    doubleBackPressed = true
                    Toast.makeText(this,"Press BACK again to EXIT",Toast.LENGTH_SHORT).show()
                    activityScope.launch {
                        delay(2000)
                        doubleBackPressed = false
                    }
                } else{
                    super.onBackPressed()
                }
            }
    }

    /**
     * 1) Cancelling activity scope when activity enters onPause()
     */
    override fun onPause() {
        activityScope.cancel() // Step 1
        super.onPause()
    }
}