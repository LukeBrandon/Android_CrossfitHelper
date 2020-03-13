package dev.lukeb.thefireground.PercentagesCalculator

import com.google.android.material.textfield.TextInputLayout
import java.lang.Math.round
import kotlin.math.roundToInt

class PercentagesCalculatorPresenter (private var view: PercentagesCalculatorActivity){

    fun readAndCalculatePercentages(numPercentages: Int, rounded: Boolean) : Array<String?> {
        val percentageResults: ArrayList<String> =  arrayListOf()
        val percentages: ArrayList<Double> = arrayListOf()

        // Read all of the percentages from the TextInputLayout
        for(i in 0..numPercentages) {
            val layout: TextInputLayout =
                view.percentagesLinearLayout.getChildAt(i) as TextInputLayout

            if(layout.editText?.text.toString() != "") {
                percentages.add(layout.editText?.text.toString().toDouble())
            }
        }
        // Read the max weight from the edit text
        val maxWeight: Double = view.maxWeightInput.text.toString().toDouble()

        // Calculate the results and put it into the results array
        for(i in 0 until percentages.size){
            var result: Double = (maxWeight * (percentages[i] * .01))

            if (rounded) {
                result = roundToNearestFive(result)
            } else {
                result = result.roundToInt().toDouble()
            }

            percentageResults.add(percentages[i].roundToInt().toString() + ": " + result.toString())
        }

        // Convert to an array of strings
        val percentagesStringArray = arrayOfNulls<String>(percentages.size)
        percentageResults.toArray(percentagesStringArray)

        return percentagesStringArray
    }

    private fun roundToNearestFive(number: Double): Double{
        return 5.0 * (number / 5).roundToInt()
    }
}