package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val entries = mutableListOf<DisplayEntry>()
    private lateinit var entriesRecyclerView: RecyclerView
    private lateinit var entryAdapter: EntryAdapter
    private lateinit var uploadButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        entriesRecyclerView = view.findViewById(R.id.entry_recycler_view)
        entriesRecyclerView.layoutManager = layoutManager
        uploadButton = view.findViewById(R.id.addButton)
        resetButton = view.findViewById(R.id.resetButton)
        entriesRecyclerView.setHasFixedSize(true)
        entryAdapter = EntryAdapter(view.context, entries)
        entriesRecyclerView.adapter = entryAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

    companion object {
        fun newInstance(): LogFragment {
            return LogFragment()
        }
    }

    private fun fetchLogs() {
        lifecycleScope.launch {
            (requireActivity().application as BitFitApplication).db.entryDao().getAll().collect { databaseList ->
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
            val intent = Intent(view?.context, DetailActivity::class.java)
            view?.context?.startActivity(intent)
            entryAdapter.notifyDataSetChanged()
        }

        resetButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (requireActivity().application as BitFitApplication).db.entryDao().deleteAll()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchLogs()
    }
}