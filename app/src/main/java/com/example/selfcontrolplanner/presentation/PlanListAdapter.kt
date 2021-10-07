package com.example.selfcontrolplanner.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanListAdapter : ListAdapter<PlannerItem, PlanItemViewHolder>(PlanItemDiffCallback()) {

    //DiffUtil.CallBack
//class PlanListAdapter : RecyclerView.Adapter<PlanListAdapter.PlanItemViewHolder>() {
//    var planList = listOf<PlannerItem>()
//        set(value) {
//            val callback = PlanListDiffCallback(planList, value)
//            val diffResult = DiffUtil.calculateDiff(callback)
//            diffResult.dispatchUpdatesTo(this)
//            field = value
//        }

    var onPlanItemLongClickListener: ((PlannerItem) -> Unit)? = null
    var onPlanItemClickListener: ((PlannerItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.enabled_plan_item
            VIEW_TYPE_DISABLED -> R.layout.disabled_plan_item
            else -> throw RuntimeException("Unknown type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PlanItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanItemViewHolder, position: Int) {
        val planItem = getItem(position)
        holder.itemView.setOnLongClickListener {
            onPlanItemLongClickListener?.invoke(planItem)
            true
        }
        holder.itemView.setOnClickListener {
            onPlanItemClickListener?.invoke(planItem)
        }
        holder.tvName.text = planItem.name
        holder.tvCount.text = planItem.data.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.deferred) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0;
        const val VIEW_TYPE_DISABLED = 1;
        const val MAX_POOL_SIZE = 15;
    }
}