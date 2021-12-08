package com.example.selfcontrolplanner.domain

class GetPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    suspend fun getPlannerItem(plannerItemId: Int) : PlannerItem {
        return plannerListRepository.getPlannerItem(plannerItemId)
    }
}