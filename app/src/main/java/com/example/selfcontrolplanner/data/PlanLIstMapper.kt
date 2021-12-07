package com.example.selfcontrolplanner.data

import com.example.selfcontrolplanner.domain.PlannerItem

class PlanLIstMapper {

    fun mapEntityToDbModel(plannerItem: PlannerItem): PlannerItemDbModel {
        return PlannerItemDbModel(
            id = plannerItem.id,
            name = plannerItem.name,
            count = plannerItem.count,
            enabled = plannerItem.enabled
        )
    }

    fun mapDbModelToEntity(plannerItemDbModel: PlannerItemDbModel): PlannerItem {
        return PlannerItem(
            id = plannerItemDbModel.id,
            name = plannerItemDbModel.name,
            count = plannerItemDbModel.count,
            enabled = plannerItemDbModel.enabled
        )
    }

    fun mapListDbModelToListEntity(list: List<PlannerItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}