package com.example.selfcontrolplanner.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.databinding.DisabledPlanItemBinding
import com.example.selfcontrolplanner.databinding.EnabledPlanItemBinding
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanListAdapter : ListAdapter<PlannerItem, PlanItemViewHolder>(PlanItemDiffCallback()) {

    var onPlanItemLongClickListener: ((PlannerItem) -> Unit)? = null
    var onPlanItemClickListener: ((PlannerItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.enabled_plan_item
            VIEW_TYPE_DISABLED -> R.layout.disabled_plan_item
            else -> throw RuntimeException("Unknown type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return PlanItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanItemViewHolder, position: Int) {
        val planItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onPlanItemLongClickListener?.invoke(planItem)
            true
        }
        binding.root.setOnClickListener {
            onPlanItemClickListener?.invoke(planItem)
        }

        when (binding) {
            is DisabledPlanItemBinding -> {
                binding.tvName.text = planItem.name
                binding.tvCount.text = planItem.data.toString()
            }

            is EnabledPlanItemBinding -> {
                binding.tvName.text = planItem.name
                binding.tvCount.text = planItem.data.toString()
            }
        }
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