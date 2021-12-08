package com.example.selfcontrolplanner.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.EditPlannerItemUseCase
import com.example.selfcontrolplanner.domain.GetPlannerListUseCase
import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.RemovePlannerItemUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PlannerListRepositoryImpl(application)

    private val getPlannerListUseCase = GetPlannerListUseCase(repository)
    private val removePlannerItemUseCase =  RemovePlannerItemUseCase(repository)
    private val editPlannerItemUseCase =  EditPlannerItemUseCase(repository)

    val plannerList = getPlannerListUseCase.getPlannerList()

    fun removePlannerItem(plannerItem: PlannerItem) {
        viewModelScope.launch {
            removePlannerItemUseCase.removePlannerItem(plannerItem)
        }
    }

    fun editPlannerList(plannerItem: PlannerItem) {
        viewModelScope.launch {
            val newItem = plannerItem.copy(enabled = !plannerItem.enabled)
            editPlannerItemUseCase.editPlannerItem(newItem)
        }
    }
}