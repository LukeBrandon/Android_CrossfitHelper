package dev.lukeb.thefireground

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var functionsRecyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var functionsRecyclerAdapter: FunctionsRecyclerAdapter

    private var functions: ArrayList<String> = ArrayList<String>()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        linearLayoutManager = LinearLayoutManager(this)
        functionsRecyclerAdapter = FunctionsRecyclerAdapter(this, functions)

        addFunctions()

        initRecyclerView()
    }

    // Initializes the recycler view layout and adapter
    private fun initRecyclerView(){
        functionsRecyclerView = findViewById<RecyclerView>(R.id.functionsRecycler).apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager;
            adapter = functionsRecyclerAdapter;
        }
    }

    private fun addFunctions(){
        functions.add("Percentages Calculator")
        functions.add("Submit new feature request")
    }
}
