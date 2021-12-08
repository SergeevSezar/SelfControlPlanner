package com.example.selfcontrolplanner.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.selfcontrolplanner.data.PlannerListRepositoryImpl
import com.example.selfcontrolplanner.domain.AddPlannerItemUseCase
import com.example.selfcontrolplanner.domain.EditPlannerItemUseCase
import com.example.selfcontrolplanner.domain.GetPlannerItemUseCase
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PlannerListRepositoryImpl(application)
    private val getPlanItemUseCase = GetPlannerItemUseCase(repository)
    private val addPlanItemUseCase = AddPlannerItemUseCase(repository)
    private val editPlanItemUseCase = EditPlannerItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _planItem = MutableLiveData<PlannerItem>()
    val planItem: LiveData<PlannerItem>
        get() = _planItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    fun getPlanItem(planItemId: Int) {
        val item = getPlanItemUseCase.getPlannerItem(planItemId)
        _planItem.value = item
    }

    fun addPlanItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val planItem = PlannerItem(name, count, true)
            addPlanItemUseCase.addPlannerItemList(planItem)
            finishWork()
        }

    }

    fun editPlanItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _planItem.value?.let {
                val item = it.copy(name = name, count = count)
                editPlanItemUseCase.editPlannerItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _closeScreen.value = Unit
    }
}