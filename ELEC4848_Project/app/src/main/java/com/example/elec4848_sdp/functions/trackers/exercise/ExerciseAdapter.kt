package com.example.elec4848_sdp.functions.trackers.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.R
import java.sql.Time
import java.util.jar.Attributes.Name

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
        private var date = view.findViewById<TextView>(R.id.Date)
        private var time = view.findViewById<TextView>(R.id.Time)
        var deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fun bindView(std: ExerciseModel) {
            val CaloriesString = StringBuilder()
            val IntensityString = StringBuilder()
            val DurationString = StringBuilder()
            val DateString = StringBuilder()
            val TimeString = StringBuilder()

            CaloriesString.append("Calories Burnt: ")
            CaloriesString.append(std.calories.toString())
            CaloriesString.append(" cal")

            IntensityString.append("Intensity Level: ")
            IntensityString.append(std.intensity)

            DurationString.append("Duration: ")
            DurationString.append(std.duration.toString())
            DurationString.append(" mins")

            DateString.append("Date: ")
            DateString.append(std.date.toString())

            TimeString.append("Time: ")
            TimeString.append(std.time.toString())

            name.text = std.name
            value.text = CaloriesString.toString()
            intensity.text = IntensityString.toString()
            duration.text = DurationString.toString()
            date.text = DateString.toString()
            time.text = TimeString.toString()
        }
    }
}