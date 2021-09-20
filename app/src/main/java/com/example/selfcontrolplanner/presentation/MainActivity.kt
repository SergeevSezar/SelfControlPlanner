package com.example.selfcontrolplanner.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var planListLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        planListLinearLayout = findViewById(R.id.ll_plan_list)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.plannerList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list:List<PlannerItem>) {
        planListLinearLayout.removeAllViews()
        for (planItem in list) {
            val layoutId = if (planItem.deferred) {
                R.layout.enabled_plan_item
            } else {
                R.layout.disabled_plan_item
            }
            val view = LayoutInflater.from(this).inflate(layoutId,planListLinearLayout, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = planItem.name
            tvCount.text = planItem.data.toString()
            view.setOnLongClickListener {
                viewModel.editPlannerList(planItem)
                true
            }
            planListLinearLayout.addView(view)
        }
    }
}