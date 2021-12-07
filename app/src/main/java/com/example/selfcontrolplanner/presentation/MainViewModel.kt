package com.example.selfcontrolplanner.presentation

import androidx.lifecycle.ViewModel
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.*

class MainViewModel : ViewModel() {

    private val repository = PlannerListRepositoryImpl

    private val getPlannerListUseCase = GetPlannerListUseCase(repository)
    private val removePlannerItemUseCase =  RemovePlannerItemUseCase(repository)
    private val editPlannerItemUseCase =  EditPlannerItemUseCase(repository)

    val plannerList = getPlannerListUseCase.getPlannerList()

    fun removePlannerItem(plannerItem: PlannerItem) {
        removePlannerItemUseCase.removePlannerItem(plannerItem)
    }

    fun editPlannerList(plannerItem: PlannerItem) {
        val newItem = plannerItem.copy(enabled = !plannerItem.enabled)
        editPlannerItemUseCase.editPlannerItem(newItem)
    }
}