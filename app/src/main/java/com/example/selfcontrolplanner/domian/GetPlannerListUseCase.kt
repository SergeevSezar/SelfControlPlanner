package com.example.selfcontrolplanner.domian

class GetPlannerListUseCase(private val plannerListRepository: PlannerListRepository) {

    fun getPlannerList(): List<PlannerItem> {
        return plannerListRepository.getPlannerList()
    }
}