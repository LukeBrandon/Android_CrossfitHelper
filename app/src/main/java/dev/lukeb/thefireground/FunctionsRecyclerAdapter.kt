package dev.lukeb.thefireground

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import dev.lukeb.thefireground.PercentagesCalculator.PercentagesCalculatorActivity

class FunctionsRecyclerAdapter(private val context: Context, private val functions: ArrayList<String>) :
    RecyclerView.Adapter<FunctionsRecyclerAdapter.FunctionsRecyclerViewHolder>() {



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FunctionsRecyclerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.functions_recyclerview_holder, parent, false) as View


        return FunctionsRecyclerViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: FunctionsRecyclerViewHolder, position: Int) {
        holder.textView.text = functions[position]
    }

    override fun getItemCount() = functions.size

    // View Holder
    class FunctionsRecyclerViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view){
        var textView: TextView = view.findViewById(R.id.functionTitle)

        init {
            textView.setOnClickListener{
                // Intent to launch
                // Find better way of doing this, this is terrible
                if(textView.text == "Percentages Calculator"){
                    val intent: Intent = Intent(context, PercentagesCalculatorActivity::class.java)
                    startActivity(context, intent, null)
                }

                if(textView.text == "Submit new feature request"){
                    Toast.makeText(context, "Contact Luke in order to make a new feature request", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
