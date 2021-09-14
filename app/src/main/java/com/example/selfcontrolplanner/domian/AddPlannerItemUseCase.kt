package com.example.selfcontrolplanner.domian

class AddPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun addPlannerItemList(plannerItem: PlannerItem) {
        plannerListRepository.addPlannerItemList(plannerItem)
    }
}