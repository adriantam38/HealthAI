package com.example.comp3330_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter: RecyclerView.Adapter<ExerciseAdapter.RecordViewHolder>() {
    private var stdList: ArrayList<ExerciseModel> = ArrayList()
    private var onClickDeleteItem: ((ExerciseModel) -> Unit)? = null

    fun addItems(items: ArrayList<ExerciseModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback:(ExerciseModel) -> Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecordViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.card_exercise_std, parent, false))

    override fun onBindViewHolder(
        holder: RecordViewHolder,
        position: Int
    ) {
        val std = stdList[position]
        holder.bindView(std)
        holder.deleteButton.setOnClickListener { onClickDeleteItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class RecordViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.Name)
        private var intensity = view.findViewById<TextView>(R.id.Intensity)
        private var duration = view.findViewById<TextView>(R.id.Duration)
        private var value = view.findViewById<TextView>(R.id.Value)
        var deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fun bindView(std:ExerciseModel) {
            name.text = std.name
            value.text = std.calories.toString()
            intensity.text = std.intensity
            duration.text = std.duration.toString()
        }
    }
}