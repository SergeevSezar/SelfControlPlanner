package com.example.selfcontrolplanner.domain

data class PlannerItem(
    val name: String,
    val data: Int,
    val deferred: Boolean,
    var id: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = -1
    }
}
