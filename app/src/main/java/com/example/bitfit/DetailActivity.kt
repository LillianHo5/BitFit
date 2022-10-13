package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var foodTextView: TextView
    private lateinit var caloriesTextView: TextView
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the views for the screen
        foodTextView = findViewById(R.id.inputFood)
        caloriesTextView = findViewById(R.id.inputCalories)
        saveButton = findViewById(R.id.button)

        saveButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db.entryDao().insert(
                    EntryEntity (
                        id = 0,
                        foodTextView.text.toString(),
                        caloriesTextView.text.toString()
                    )
                )
            }
            finish()
        }
    }
}
