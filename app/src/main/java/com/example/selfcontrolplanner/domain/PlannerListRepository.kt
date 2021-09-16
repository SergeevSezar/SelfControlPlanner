package com.example.selfcontrolplanner.domain

import androidx.lifecycle.LiveData

interface PlannerListRepository {

    fun getPlannerList(): LiveData<List<PlannerItem>>

    fun getPlannerItem(plannerItemId: Int): PlannerItem

    fun editPlannerItem(plannerItem: PlannerItem)

    fun addPlannerItemList(plannerItem: PlannerItem)

    fun removePlannerItem(plannerItem: PlannerItem)
}