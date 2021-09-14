package com.example.selfcontrolplanner.domain

class GetPlannerListUseCase(private val plannerListRepository: PlannerListRepository) {

    fun getPlannerList(): List<PlannerItem> {
        return plannerListRepository.getPlannerList()
    }
}