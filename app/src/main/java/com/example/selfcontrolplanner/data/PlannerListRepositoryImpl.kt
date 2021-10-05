package com.example.selfcontrolplanner.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.PlannerListRepository

object PlannerListRepositoryImpl : PlannerListRepository {

    private val plannerListLD = MutableLiveData<List<PlannerItem>>()
    private val plannerList = sortedSetOf<PlannerItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncId = 0

    init {
        for(i in 0 until 1000) {
            val item = PlannerItem("Name $i", i, true)
            addPlannerItemList(item)
        }
    }

    override fun getPlannerList(): LiveData<List<PlannerItem>> {
        return plannerListLD
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
        updateList()
    }

    override fun removePlannerItem(plannerItem: PlannerItem) {
        plannerList.remove(plannerItem)
        updateList()
    }

    private fun updateList() {
        plannerListLD.postValue(plannerList.toList())
    }
}