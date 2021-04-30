package com.example.triviaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.triviaapp.R

class ChooseCricketerFragment : Fragment() {
    private var radioGroup : RadioGroup? = null
    private var btnNext : Button? = null
    private val args : ChooseCricketerFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_cricketer, container, false)
    }

    /**
     * 1) Getting the values from safe args
     * 2) Declaring and initialising the views
     * 3) Setting up click listener for button next
     * 4) Getting the checked radio button id from radio group
     * 5) Getting the instance of checked radio button using the radio button id obtained
     * 6) Getting the answer selected by getting the checked radio button text
     * 7) Navigating to Choose  color fragment
     * 8) If the checked radio button id returns null then that means no options are selected, then shows a toast message
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name // Step 1

        radioGroup = view.findViewById(R.id.rbGroup) // Step 2
        btnNext = view.findViewById(R.id.btnNext)
        val navigationController : NavController = Navigation.findNavController(view)

        btnNext?.setOnClickListener { // Step 3
            val radioButtonId = radioGroup?.checkedRadioButtonId // Step 4
            if (radioButtonId!=null){
                val selectedRadioButton : RadioButton = view.findViewById(radioButtonId) // Step 5
                val cricketer = selectedRadioButton.text.toString() // Step 6
                val action = ChooseCricketerFragmentDirections.actionChooseCricketerFragmentToChooseColorFragment(name,cricketer)  // Step 7
                navigationController.navigate(action)
            } else{
                Toast.makeText(activity,"Please select a cricketer to continue",Toast.LENGTH_SHORT).show() // Step 8
            }
        }
    }
}