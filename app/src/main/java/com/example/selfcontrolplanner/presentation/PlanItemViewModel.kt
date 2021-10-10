package com.example.selfcontrolplanner.presentation

import androidx.lifecycle.ViewModel
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.AddPlannerItemUseCase
import com.example.selfcontrolplanner.domain.EditPlannerItemUseCase
import com.example.selfcontrolplanner.domain.GetPlannerItemUseCase
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanItemViewModel: ViewModel() {

    private val repository = PlannerListRepositoryImpl

    private val getPlanItemUseCase = GetPlannerItemUseCase(repository)
    private val addPlanItemUseCase = AddPlannerItemUseCase(repository)
    private val editPlanItemUseCase = EditPlannerItemUseCase(repository)

    fun getPlanItem(planItemId: Int) {
        val item = getPlanItemUseCase.getPlannerItem(planItemId)
    }

    fun addPlanItem(planItem: PlannerItem) {
        addPlanItemUseCase.addPlannerItemList(planItem)
    }

    fun editPlanItem(planItem: PlannerItem) {
        editPlanItemUseCase.editPlannerItem(planItem)
    }
}