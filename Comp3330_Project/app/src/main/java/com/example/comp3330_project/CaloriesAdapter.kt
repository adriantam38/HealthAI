package com.example.comp3330_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CaloriesAdapter: RecyclerView.Adapter<CaloriesAdapter.RecordViewHolder>() {
    private var stdList: ArrayList<CaloriesModel> = ArrayList()
    private var onClickDeleteItem: ((CaloriesModel) -> Unit)? = null

    fun addItems(items: ArrayList<CaloriesModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback:(CaloriesModel) -> Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecordViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.card_calories_std, parent, false))

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
        private var value = view.findViewById<TextView>(R.id.Value)
        var deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fun bindView(std:CaloriesModel) {
            name.text = std.name
            value.text = std.calories.toString()
        }
    }
}