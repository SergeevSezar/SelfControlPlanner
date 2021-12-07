package com.example.selfcontrolplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_items")
data class PlannerItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)