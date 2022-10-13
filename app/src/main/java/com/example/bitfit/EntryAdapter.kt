package com.example.bitfit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EntryAdapter(private val context: Context, private val entries: MutableList<DisplayEntry>) :
    RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_entry, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val foodTextView = itemView.findViewById<TextView>(R.id.foodName)
        private val caloriesTextView = itemView.findViewById<TextView>(R.id.numCalories)

        init {
            itemView.setOnClickListener(this)
        }

        // Write a helper method to help set up the onBindViewHolder method
        fun bind(entry: DisplayEntry) {
            foodTextView.text = entry.food
            caloriesTextView.text = entry.calories
        }

        override fun onClick(v: View?) {
            /*
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            */
        }
    }
}