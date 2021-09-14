package com.example.selfcontrolplanner.domian

class EditPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun editPlannerItem(plannerItem: PlannerItem) {
        plannerListRepository.editPlannerItem(plannerItem)
    }
}