package com.example.triviaapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.triviaapp.R
import com.example.triviaapp.activities.HistoryActivity

class EnterNameFragment : Fragment() {
    private var etName : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_name, container, false)
    }

    /**
     * 1) Initialising and declaring the views
     * 2) Setting up button click listener
     * 3) Checking if a name is entered or not
     * 4) If name is not entered then shows a toast message
     * 5) If entered then navigate to next page with the entered name
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etName = view.findViewById(R.id.etName) // Step 1
        val navigationController : NavController = Navigation.findNavController(view)
        val button : Button = view.findViewById(R.id.btnNext)

        button.setOnClickListener { // Step 2
            if (etName?.text?.toString()?.trim()?.isEmpty()!!){ // Step 3
                Toast.makeText(activity,"Please enter a name to continue",Toast.LENGTH_SHORT).show() // Step 4
            } else{
                val action = EnterNameFragmentDirections.actionEnterNameFragmentToChooseCricketerFragment(etName?.text.toString().trim()) // Step 5
                navigationController.navigate(action)
            }
        }
    }
}