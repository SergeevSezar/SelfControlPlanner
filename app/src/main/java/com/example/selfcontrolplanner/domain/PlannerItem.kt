package com.example.selfcontrolplanner.domain

data class PlannerItem(
    val id : Int,
    val name : String,
    val data : Int,
    val deferred : Boolean
)
