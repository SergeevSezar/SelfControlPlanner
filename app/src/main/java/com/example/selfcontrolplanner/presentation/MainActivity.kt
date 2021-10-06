package com.example.selfcontrolplanner.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.selfcontrolplanner.R

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
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvPlanList)
    }

    private fun setupSwipeListener(rvPlanList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.planList[viewHolder.adapterPosition]
                viewModel.removePlannerItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPlanList)
    }

    private fun setupClickListener() {
        adapter.onPlanItemClickListener = {
            Log.d("MainActivity", it.toString())
        }
    }

    private fun setupLongClickListener() {
        adapter.onPlanItemLongClickListener = {
            viewModel.editPlannerList(it)
        }
    }
}
