package com.csci448.lsherburne.A1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

// constant variables
const val SLICES_PER_PIZZA = 8
const val LIGHT_SLICES = 1
const val MEDIUM_SLICES = 2
const val RAVENOUS_SLICES = 4

class MainActivity : AppCompatActivity() {
    private var numPizzas : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to buttons
        val calculateTotalCost = findViewById<Button>(R.id.calculate_button)    // calculate button
        val refreshCost = findViewById<Button>(R.id.refresh_cost_button)    // refresh cost button
        val howHungry = findViewById<RadioGroup>(R.id.how_hungry_radio_group)    // how hungry input

        // get reference to text
        val costPerPizzaInput = findViewById<EditText>(R.id.cost_per_pizza)  // cost per pizza input, default is 5.55
        val numberPeopleInput = findViewById<EditText>(R.id.number_of_people)    // number of people input
        val totalCostTextView = findViewById<TextView>(R.id.total_cost) // total cost text view
        val totalPizzasNeeded = findViewById<TextView>(R.id.total_pizzas_needed)    // total pizzas needed text view

        // get reference to layouts
        val totalCostLayout = findViewById<LinearLayout>(R.id.total_cost_layout) // total cost layout
        val costPerPizzaLayout = findViewById<LinearLayout>(R.id.cost_per_pizza_layout) // cost per pizza layout

        // set click listener for calculate button
        calculateTotalCost.setOnClickListener {
            // if first calculate button click, make the invisible elements visible
            if (totalCostLayout.visibility == View.INVISIBLE) {
                totalCostLayout.visibility = View.VISIBLE
                refreshCost.visibility = View.VISIBLE
                costPerPizzaLayout.visibility = View.VISIBLE
            }

            // get hunger level from radio button group
            val selectedButton: Int = howHungry!!.checkedRadioButtonId
            val selectedHungerButton: RadioButton = findViewById(selectedButton)

            // get number of people and price of pizza
            val numberPeople = numberPeopleInput.getText().toString().toInt()
            val costPerPizza = costPerPizzaInput.getText().toString().toDouble()

            // perform calculations
            numPizzas = calcTotalCost(selectedHungerButton, numberPeople)
            val totalCost = numPizzas * costPerPizza
            val output = String.format("%.2f", totalCost)

            // set output to the screen
            totalPizzasNeeded.text = "$numPizzas"
            totalCostTextView.text = "$$output"
        }

        // set click listener for refresh cost button
        refreshCost.setOnClickListener {
            // calculate cost using same number of pizzas, but updated cost
            val costPerPizza = costPerPizzaInput.getText().toString().toDouble()
            val totalCost = costPerPizza * numPizzas
            val output = String.format("%.2f", totalCost)

            // set output to the screen
            totalCostTextView.text = "$$output"
        }
    }

    // function to calculate cost given the number of people and level of hunger
    fun calcTotalCost(selHunger : RadioButton, numPeople : Int) : Int {
        var numberPizzas = 0
        // perform calculation
        var numSlices = 0
        if (selHunger.id == R.id.light_radio_button) {
            // 1 slice per person
            numSlices = LIGHT_SLICES * numPeople
            numberPizzas = (LIGHT_SLICES * numPeople) / SLICES_PER_PIZZA
        } else if (selHunger.id == R.id.medium_radio_button) {
            // 2 slices per person
            numSlices = MEDIUM_SLICES * numPeople
            numberPizzas = (MEDIUM_SLICES * numPeople) / SLICES_PER_PIZZA
        } else if (selHunger.id == R.id.ravenous_radio_button) {
            // 4 slices per person
            numSlices = RAVENOUS_SLICES * numPeople
            numberPizzas = (RAVENOUS_SLICES * numPeople) / SLICES_PER_PIZZA
        }

        // check for needing another pizza
        if (numSlices % SLICES_PER_PIZZA != 0) {
            numberPizzas++
        }

        // return
        return numberPizzas
    }
}