package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private lateinit var averageCaloriesView: TextView
    private lateinit var minCaloriesView: TextView
    private lateinit var maxCaloriesView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        var avgCalories = 0;
        var minCalories = 0;
        var maxCalories = 0;
        averageCaloriesView = view.findViewById(R.id.average_calories)
        minCaloriesView = view.findViewById(R.id.min_calories)
        maxCaloriesView = view.findViewById(R.id.max_calories)


        lifecycleScope.launch(Dispatchers.IO){
            avgCalories = (activity?.application as BitFitApplication).db.entryDao().averageCalories()
            averageCaloriesView.text = avgCalories.toString()

            minCalories = (activity?.application as BitFitApplication).db.entryDao().minCalories()
            minCaloriesView.text = minCalories.toString()

            maxCalories = (activity?.application as BitFitApplication).db.entryDao().maxCalories()
            maxCaloriesView.text = maxCalories.toString()
        }

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}