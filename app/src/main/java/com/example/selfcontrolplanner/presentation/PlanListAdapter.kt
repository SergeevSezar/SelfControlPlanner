package com.example.selfcontrolplanner.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanListAdapter: RecyclerView.Adapter<PlanListAdapter.PlanItemViewHolder> () {

    val list = listOf<PlannerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.enabled_plan_item, parent, false)
        return PlanItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanItemViewHolder, position: Int) {
       val planItem = list[position]
        holder.tvName.text = planItem.name
        holder.tvCount.text = planItem.data.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PlanItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}