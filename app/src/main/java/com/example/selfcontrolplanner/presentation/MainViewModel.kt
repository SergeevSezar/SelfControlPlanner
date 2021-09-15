package com.example.selfcontrolplanner.presentation

import androidx.lifecycle.ViewModel
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.EditPlannerItemUseCase
import com.example.selfcontrolplanner.domain.GetPlannerListUseCase
import com.example.selfcontrolplanner.domain.RemovePlannerItemUseCase

class MainViewModel : ViewModel() {

    private val repository = PlannerListRepositoryImpl

    private val getPlannerListUseCase = GetPlannerListUseCase(repository)
    private val removePlannerItemUseCase =  RemovePlannerItemUseCase(repository)
    private val editPlannerItemUseCase =  EditPlannerItemUseCase(repository)
}