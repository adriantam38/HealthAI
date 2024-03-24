package com.example.elec4848_sdp.functions.trackers.calories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.R

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
        private var date = view.findViewById<TextView>(R.id.Date)
        private var time = view.findViewById<TextView>(R.id.Time)
        var deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fun bindView(std: CaloriesModel) {
            val CaloriesString = StringBuilder()
            val DateString = StringBuilder()
            val TimeString = StringBuilder()

            CaloriesString.append("Calories Consumed: ")
            CaloriesString.append(std.calories.toString())
            CaloriesString.append(" cal")

            DateString.append("Date: ")
            DateString.append(std.date.toString())

            TimeString.append("Time: ")
            TimeString.append(std.time.toString())

            name.text = std.name
            value.text = CaloriesString.toString()
            date.text = DateString.toString()
            time.text = TimeString.toString()
        }
    }
}