package com.example.selfcontrolplanner.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlannerListDao {

    @Query("SELECT * FROM plan_items")
    fun getPlanList(): LiveData<List<PlannerItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlanItem(plannerItemDbModel: PlannerItemDbModel)

    @Query("DELETE FROM plan_items WHERE id=:planItemId")
    suspend fun deletePlanItem(planItemId: Int)

    @Query("SELECT * FROM plan_items WHERE id=:planItemId LIMIT 1")
    suspend fun getPlan(planItemId: Int): PlannerItemDbModel
}