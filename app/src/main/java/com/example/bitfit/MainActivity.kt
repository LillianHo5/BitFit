package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val entries = mutableListOf<DisplayEntry>()
    private lateinit var entriesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var uploadButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        uploadButton = findViewById(R.id.addButton)
        resetButton = findViewById(R.id.resetButton)
        entriesRecyclerView = findViewById(R.id.entries)
        val entryAdapter = EntryAdapter(this, entries)
        entriesRecyclerView.adapter = entryAdapter

        entriesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            entriesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            (application as BitFitApplication).db.entryDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.food,
                        entity.calories
                    )
                }.also { mappedList ->
                    entries.clear()
                    entries.addAll(mappedList)
                    entryAdapter.notifyDataSetChanged()
                }
            }
        }

        uploadButton.setOnClickListener {
            val intent = Intent(view.context, DetailActivity::class.java)
            view.context?.startActivity(intent)
            entryAdapter.notifyDataSetChanged()
        }

        resetButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFitApplication).db.entryDao().deleteAll()
            }
        }
    }
}