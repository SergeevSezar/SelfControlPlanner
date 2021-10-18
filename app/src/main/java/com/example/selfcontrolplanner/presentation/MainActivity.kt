package com.example.selfcontrolplanner.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.selfcontrolplanner.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlanListAdapter
    private var planItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        planItemContainer = findViewById(R.id.plan_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.plannerList.observe(this) {
            adapter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.btn_add_plan_item)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = PlanItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(PlanItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return planItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.plan_item_container, fragment)
            .addToBackStack(null)
            .commit()
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
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.removePlannerItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPlanList)
    }

    private fun setupClickListener() {
        adapter.onPlanItemClickListener = {
            if (isOnePaneMode()) {
                val intent = PlanItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(PlanItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        adapter.onPlanItemLongClickListener = {
            viewModel.editPlannerList(it)
        }
    }
}
