package com.example.selfcontrolplanner.domain

import androidx.lifecycle.LiveData

class GetPlannerListUseCase(private val plannerListRepository: PlannerListRepository) {

    fun getPlannerList(): LiveData<List<PlannerItem>> {
        return plannerListRepository.getPlannerList()
    }
}