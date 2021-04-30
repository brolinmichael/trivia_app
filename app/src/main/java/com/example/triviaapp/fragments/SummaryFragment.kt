package com.example.triviaapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.triviaapp.R
import com.example.triviaapp.activities.HistoryActivity
import com.example.triviaapp.activities.HomeActivity

class SummaryFragment : Fragment() {
    private val args : SummaryFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    /**
     * 1) Getting the values from safe args
     * 2) Hiding the toolbar in Summary fragment
     * 3) Creating a thread to perform database insert operation
     * 4) Calling insert to db function written in Home activity
     * 5) Initialising the views
     * 6) Setting the values received from safe args to views
     * 7) Setting up finish button click listener
     * 8) Restarting the game by restarting activity
     * 9) Setting up history button click listener
     * 10) Calling history activity using intent
     * 11) Specifying activity transition animation
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name // Step 1
        val cricketer = args.cricketer
        val color = args.color

        (activity as HomeActivity).toolBar?.visibility = View.GONE // Step 2

        Thread{ // Step 3
            (activity as HomeActivity).insert(name,cricketer,color)
        }.start()

        val tvName : TextView = view.findViewById(R.id.tvName) // Step 4
        val tvCricketer : TextView = view.findViewById(R.id.tvCricketer)
        val tvColor : TextView = view.findViewById(R.id.tvColor)
        val btnFinish : Button = view.findViewById(R.id.btnFinish)
        val btnHistory : Button = view.findViewById(R.id.btnHistory)

        tvName.text = "Hello $name," // Step 5
        tvCricketer.text = cricketer
        tvColor.text = color


        btnFinish.setOnClickListener { // Step 6
            val intent = Intent(activity,HomeActivity::class.java) // Step 7
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity?.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        btnHistory.setOnClickListener { // Step 8
            startActivity(Intent(activity,HistoryActivity::class.java)) // Step 9
            activity?.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out) // Step 10
        }
    }
}