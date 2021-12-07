package com.example.selfcontrolplanner.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.PlannerListRepository

class PlannerListRepositoryImpl(application: Application) : PlannerListRepository {

    private val plannerListDao = AppDataBase.getInstance(application).plannerListDao()
    private val mapper = PlanLIstMapper()

    override fun getPlannerList(): LiveData<List<PlannerItem>> {
        return TODO()
    }

    override fun getPlannerItem(plannerItemId: Int): PlannerItem {
        val dbModel = plannerListDao.getPlan(plannerItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun editPlannerItem(plannerItem: PlannerItem) {
        plannerListDao.addPlanItem(mapper.mapEntityToDbModel(plannerItem))
    }

    override fun addPlannerItemList(plannerItem: PlannerItem) {
       plannerListDao.addPlanItem(mapper.mapEntityToDbModel(plannerItem))
    }

    override fun removePlannerItem(plannerItem: PlannerItem) {
        plannerListDao.deletePlanItem(plannerItem.id)
    }
}