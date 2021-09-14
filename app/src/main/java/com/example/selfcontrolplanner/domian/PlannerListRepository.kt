package com.example.selfcontrolplanner.domian

interface PlannerListRepository {

    fun getPlannerList(): List<PlannerItem>

    fun getPlannerItem(plannerItemId: Int) : PlannerItem

    fun editPlannerItem(plannerItem: PlannerItem)

    fun addPlannerItemList(plannerItem: PlannerItem)

    fun removePlannerItem(plannerItem: PlannerItem)
}