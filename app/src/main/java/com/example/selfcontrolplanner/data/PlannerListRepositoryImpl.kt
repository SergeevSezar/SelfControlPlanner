package com.example.selfcontrolplanner.data

import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.PlannerListRepository
import java.lang.RuntimeException

object PlannerListRepositoryImpl : PlannerListRepository {

    private val plannerList = mutableListOf<PlannerItem>()

    private var autoIncId = 0

    override fun getPlannerList(): List<PlannerItem> {
        return plannerList.toList()
    }

    override fun getPlannerItem(plannerItemId: Int): PlannerItem {
        return plannerList.find {
            it.id == plannerItemId
        } ?: throw RuntimeException("Element with id $plannerItemId not found")
    }

    override fun editPlannerItem(plannerItem: PlannerItem) {
        val oldElement = getPlannerItem(plannerItem.id)
        plannerList.remove(oldElement)
        addPlannerItemList(plannerItem)
    }

    override fun addPlannerItemList(plannerItem: PlannerItem) {
        if (plannerItem.id == PlannerItem.UNDEFINED_ID) {
            plannerItem.id = autoIncId
            autoIncId++
        }
        plannerList.add(plannerItem)
    }

    override fun removePlannerItem(plannerItem: PlannerItem) {
        plannerList.remove(plannerItem)
    }
}