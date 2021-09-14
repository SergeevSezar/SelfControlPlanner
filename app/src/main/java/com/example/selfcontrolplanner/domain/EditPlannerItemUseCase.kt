package com.example.selfcontrolplanner.domain

class EditPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun editPlannerItem(plannerItem: PlannerItem) {
        plannerListRepository.editPlannerItem(plannerItem)
    }
}