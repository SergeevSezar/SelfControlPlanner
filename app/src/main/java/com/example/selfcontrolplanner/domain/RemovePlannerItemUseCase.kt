package com.example.selfcontrolplanner.domain

class RemovePlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun removePlannerItem(plannerItem: PlannerItem) {
        plannerListRepository.removePlannerItem(plannerItem)
    }
}