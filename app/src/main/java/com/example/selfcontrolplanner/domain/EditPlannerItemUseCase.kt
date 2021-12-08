package com.example.selfcontrolplanner.domain

class EditPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    suspend fun editPlannerItem(plannerItem: PlannerItem) {
        plannerListRepository.editPlannerItem(plannerItem)
    }
}