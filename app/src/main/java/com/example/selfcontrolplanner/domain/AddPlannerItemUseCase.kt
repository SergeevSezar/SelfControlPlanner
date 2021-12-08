package com.example.selfcontrolplanner.domain

class AddPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    suspend fun addPlannerItemList(plannerItem: PlannerItem) {
        plannerListRepository.addPlannerItemList(plannerItem)
    }
}