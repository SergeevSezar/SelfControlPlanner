package com.example.selfcontrolplanner.domain

import androidx.lifecycle.LiveData

interface PlannerListRepository {

    fun getPlannerList(): LiveData<List<PlannerItem>>

    suspend fun getPlannerItem(plannerItemId: Int): PlannerItem

    suspend fun editPlannerItem(plannerItem: PlannerItem)

    suspend fun addPlannerItemList(plannerItem: PlannerItem)

    suspend fun removePlannerItem(plannerItem: PlannerItem)
}