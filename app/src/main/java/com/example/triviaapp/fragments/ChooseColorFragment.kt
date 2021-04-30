package com.example.triviaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.triviaapp.R


class ChooseColorFragment : Fragment() {
    private var cbWhite : CheckBox? = null
    private var cbYellow : CheckBox? = null
    private var cbGreen : CheckBox? = null
    private var cbOrange : CheckBox? = null
    private var btnNext : Button? = null
    private var name : String? = null
    private var cricketer : String? = null
    private var navigationController : NavController? = null
    private val args : ChooseColorFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_color, container, false)
    }

    /**
     * 1) Getting the values from safe args
     * 2) Declaring and initialising the views
     * 3) Setting up click listener for button next
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = args.name // Step 1
        cricketer = args.cricketer

        navigationController = Navigation.findNavController(view) // Step 2
        btnNext = view.findViewById(R.id.btnNext)
        cbWhite = view.findViewById(R.id.cbWhite)
        cbYellow = view.findViewById(R.id.cbYellow)
        cbGreen = view.findViewById(R.id.cbGreen)
        cbOrange = view.findViewById(R.id.cbOrange)

        btnNext?.setOnClickListener { // Step 3
            validate()
        }
    }

    /**
     * 1) Initialising an array list colors
     * 2) If checkbox white is clicked then color White is added to array list colors
     * 3) If checkbox yellow is clicked then color Yellow is added to array list colors
     * 4) If checkbox green is clicked then color Green is added to array list colors
     * 5) If checkbox orange is clicked then color Orange is added to array list colors
     * 6) Checking whether the size of list colors is at least 2
     * 7) If not then showing toast message to select at least 2 colors
     * 8) If yes then getting the values from the list, then after appending it the value is passed as safe args and next summary fragment is called
     */
    private fun validate() {
        val colors : ArrayList<String> = ArrayList() // Step 1

        if (cbWhite?.isChecked!!){ // Step 2
            colors.add("White")
        }
        if (cbYellow?.isChecked!!){ // Step 3
            colors.add("Yellow")
        }
        if (cbGreen?.isChecked!!){ // Step 4
            colors.add("Green")
        }
        if (cbOrange?.isChecked!!){ // Step 5
            colors.add("Orange")
        }

        if (colors.size<2){ // Step 6
            Toast.makeText(activity,"Please select atleast two colors to continue",Toast.LENGTH_SHORT).show() // Step 7
        } else{
            val stringBuffer = StringBuffer() // Step 8
            for (i in 0 until colors.size){
                if (i == colors.size-1){
                    stringBuffer.append(colors[i]+".")
                } else{
                    stringBuffer.append(colors[i]+", ")
                }
            }
            val action = ChooseColorFragmentDirections.actionChooseColorFragmentToSummaryFragment(name!!,cricketer!!,stringBuffer.toString())
            navigationController?.navigate(action)
        }

    }
}