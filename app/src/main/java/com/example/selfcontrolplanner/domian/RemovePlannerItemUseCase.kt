package com.example.selfcontrolplanner.domian

class RemovePlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun removePlannerItem(plannerItem: PlannerItem) {
        plannerListRepository.removePlannerItem(plannerItem)
    }
}