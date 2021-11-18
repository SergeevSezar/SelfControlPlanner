package com.example.selfcontrolplanner.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.zip.Inflater

class MainActivity : AppCompatActivity(), PlanItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlanListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.plannerList.observe(this) {
            adapter.submitList(it)
        }
        binding.btnAddPlanItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = PlanItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
            launchFragment(PlanItemFragment.newInstanceAddItem())
        }
    }
}

override fun onEditingFinished() {
    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
    supportFragmentManager.popBackStack()
}

private fun isOnePaneMode(): Boolean {
    return binding.planItemContainer == null
}

private fun launchFragment(fragment: Fragment) {
    supportFragmentManager.popBackStack()
    supportFragmentManager.beginTransaction()
        .replace(R.id.plan_item_container, fragment)
        .addToBackStack(null)
        .commit()
}

private fun setupRecyclerView() {
    binding.rvPlannerList.adapter = adapter
    binding.rvPlannerList.recycledViewPool.setMaxRecycledViews(
        PlanListAdapter.VIEW_TYPE_DISABLED,
        PlanListAdapter.MAX_POOL_SIZE
    )
    binding.rvPlannerList.recycledViewPool.setMaxRecycledViews(
        PlanListAdapter.VIEW_TYPE_DISABLED,
        PlanListAdapter.MAX_POOL_SIZE
    )
    setupLongClickListener()
    setupClickListener()
    setupSwipeListener(binding.rvPlannerList)
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
