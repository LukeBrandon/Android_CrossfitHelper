package dev.lukeb.thefireground.PercentagesCalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.lukeb.thefireground.R
import androidx.appcompat.app.AlertDialog

class PercentagesCalculatorActivity : AppCompatActivity() {
    private lateinit var presenter: PercentagesCalculatorPresenter
    lateinit var maxWeightInput: TextInputEditText

    lateinit var percentagesLinearLayout: LinearLayout
    private lateinit var addPercentageButton: Button
    private lateinit var calculateButton: Button
    private lateinit var roundedSwitch: Switch

    private var numPercentages: Int = 0
    private var shouldRound: Boolean = false
    var percentages: ArrayList<Int> =  arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percentages_calculator)

        presenter = PercentagesCalculatorPresenter(this)

        initUi()
    }

    private fun initUi(){
        maxWeightInput = findViewById(R.id.weightInput)
        percentagesLinearLayout = findViewById(R.id.percentagesLinearLayout)
        addPercentageInput()

        // Button for adding another percentage
        addPercentageButton = findViewById(R.id.addPercentageButton)
        addPercentageButton.setOnClickListener {
            addPercentageInput()
            numPercentages += 1
        }

        roundedSwitch = findViewById(R.id.roundSwitch)
        roundedSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            shouldRound = isChecked
        }

        // Button for calculating
        calculateButton = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val stringArray: Array<String?> = presenter.readAndCalculatePercentages(numPercentages, shouldRound)
            showPercentages(stringArray)
        }
    }

    private fun addPercentageInput(){
        val padding = 30;

        val editText = TextInputEditText(this)
        editText.hint = "Add percentage (ex: 62)"
        editText.setPadding(padding, padding, padding, padding)
        editText.setRawInputType(InputType.TYPE_CLASS_NUMBER)
        editText.setLines(1)
        editText.setSingleLine(true)
        editText.requestFocus()

        val inputLayout = TextInputLayout(this)
        inputLayout.addView(editText)

        // Add EditText to LinearLayout
        percentagesLinearLayout.addView(inputLayout)
    }

    fun showPercentages(percentagesStringArray: Array<String?>){
        AlertDialog.Builder(this)
            .setTitle("Here are your Percentages")
            .setItems(percentagesStringArray, null)
            .setPositiveButton("Okay", { dialog, which ->
                dialog.dismiss()
            })
            .show()
    }

}
