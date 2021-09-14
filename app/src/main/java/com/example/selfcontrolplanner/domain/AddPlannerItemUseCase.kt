package com.example.selfcontrolplanner.domain

class AddPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun addPlannerItemList(plannerItem: PlannerItem) {
        plannerListRepository.addPlannerItemList(plannerItem)
    }
}