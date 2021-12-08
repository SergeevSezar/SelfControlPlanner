package com.example.selfcontrolplanner.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.EditPlannerItemUseCase
import com.example.selfcontrolplanner.domain.GetPlannerListUseCase
import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.RemovePlannerItemUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PlannerListRepositoryImpl(application)

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