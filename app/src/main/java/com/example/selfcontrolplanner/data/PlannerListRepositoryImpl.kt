package com.example.selfcontrolplanner.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.selfcontrolplanner.domain.PlannerItem
import com.example.selfcontrolplanner.domain.PlannerListRepository

class PlannerListRepositoryImpl(application: Application) : PlannerListRepository {

    private val plannerListDao = AppDataBase.getInstance(application).plannerListDao()
    private val mapper = PlanLIstMapper()

//    override fun getPlannerList(): LiveData<List<PlannerItem>> = MediatorLiveData<List<PlannerItem>>().apply {
//        addSource(plannerListDao.getPlanList()) {
//            value = mapper.mapListDbModelToListEntity(it)
//        }
//    }

    override fun getPlannerList(): LiveData<List<PlannerItem>> = Transformations.map(plannerListDao.getPlanList()) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getPlannerItem(plannerItemId: Int): PlannerItem {
        val dbModel = plannerListDao.getPlan(plannerItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun editPlannerItem(plannerItem: PlannerItem) {
        plannerListDao.addPlanItem(mapper.mapEntityToDbModel(plannerItem))
    }

    override suspend fun addPlannerItemList(plannerItem: PlannerItem) {
       plannerListDao.addPlanItem(mapper.mapEntityToDbModel(plannerItem))
    }

    override suspend fun removePlannerItem(plannerItem: PlannerItem) {
        plannerListDao.deletePlanItem(plannerItem.id)
    }
}