package com.example.selfcontrolplanner.domain

class GetPlannerItemUseCase(private val plannerListRepository: PlannerListRepository) {

    fun getPlannerItem(plannerItemId: Int) : PlannerItem {
        return plannerListRepository.getPlannerItem(plannerItemId)
    }
}