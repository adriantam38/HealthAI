package com.example.elec4848_sdp.functions.exercisetraining.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.functions.exercisetraining.adapters.RecentActivityAdapter
import com.example.elec4848_sdp.functions.exercisetraining.data.database.AppRepository
import com.example.elec4848_sdp.functions.exercisetraining.data.results.RecentActivityItem
import com.example.elec4848_sdp.functions.exercisetraining.data.results.WorkoutResult
import com.example.elec4848_sdp.functions.exercisetraining.util.MemoryManagement
import com.example.elec4848_sdp.functions.exercisetraining.util.MyApplication
import com.example.elec4848_sdp.functions.exercisetraining.util.MyUtils
import com.example.elec4848_sdp.functions.exercisetraining.viewmodels.ResultViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), MemoryManagement {
    @Suppress("PropertyName")
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var recentActivityRecyclerView: RecyclerView
    private lateinit var recentActivityAdapter: RecentActivityAdapter
    private var workoutResults: List<WorkoutResult>? = null
    private lateinit var appRepository: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView and its adapter for recent activity
        recentActivityRecyclerView = view.findViewById(R.id.recentActivityRecyclerView)
        recentActivityAdapter = RecentActivityAdapter(emptyList())
        recentActivityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recentActivityRecyclerView.adapter = recentActivityAdapter
        appRepository = AppRepository(requireActivity().application)
        // Initialize ViewModel
        resultViewModel = ResultViewModel(MyApplication.getInstance())
        lifecycleScope.launch {
            val workoutResults = resultViewModel.getRecentWorkout()
            // Call the function to load data and set up the chart
            // Transform WorkoutResult objects into RecentActivityItem objects
            val imageResources = arrayOf(R.drawable.blue, R.drawable.green, R.drawable.orange)
            // Transform WorkoutResult objects into RecentActivityItem objects
            val recentActivityItems = workoutResults?.mapIndexed { index, it ->
                RecentActivityItem(
                    imageResId = imageResources[index % imageResources.size],
                    exerciseType = MyUtils.exerciseNameToDisplay(it.exerciseName),
                    reps = "${it.repeatedCount} reps"
                )
            }
            // Update the adapter with the transformed data
            recentActivityAdapter.updateData(recentActivityItems ?: emptyList())
            // Check if the recentActivityItems list is empty
            if (recentActivityItems.isNullOrEmpty()) {
                recentActivityRecyclerView.isVisible = false
                // Show a message or handle the empty case as per your UI requirements
                val noActivityMessage = view.findViewById<TextView>(R.id.no_activity_message)
                noActivityMessage.text = getString(R.string.no_activities_yet)
                noActivityMessage.isVisible = true
            } else {
                recentActivityRecyclerView.isVisible = true
            }
        }
    }


    override fun clearMemory() {
        workoutResults = null
    }

    override fun onDestroy() {
        clearMemory()
        super.onDestroy()
    }
}
