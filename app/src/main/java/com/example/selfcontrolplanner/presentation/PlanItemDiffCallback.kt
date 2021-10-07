package com.example.selfcontrolplanner.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanItemDiffCallback: DiffUtil.ItemCallback<PlannerItem>() {
    override fun areItemsTheSame(oldItem: PlannerItem, newItem: PlannerItem): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlannerItem, newItem: PlannerItem): Boolean {
        return oldItem == newItem
    }
}