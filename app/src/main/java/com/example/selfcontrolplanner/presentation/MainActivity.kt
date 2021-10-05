package com.example.selfcontrolplanner.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlanListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.plannerList.observe(this) {
            adapter.planList = it
        }
    }

    private fun setupRecyclerView() {
        val rvPlanList = findViewById<RecyclerView>(R.id.rv_planner_list)
        adapter = PlanListAdapter()
        rvPlanList.adapter = adapter
        rvPlanList.recycledViewPool.setMaxRecycledViews(
            PlanListAdapter.VIEW_TYPE_DISABLED,
            PlanListAdapter.MAX_POOL_SIZE
        )
        rvPlanList.recycledViewPool.setMaxRecycledViews(
            PlanListAdapter.VIEW_TYPE_DISABLED,
            PlanListAdapter.MAX_POOL_SIZE
        )
        adapter.onPlanItemLongClickListener = {
            viewModel.editPlannerList(it)
        }
        adapter.onPlanItemClickListener = {
            Log.d("MainActivity", it.toString())
        }
    }
}
