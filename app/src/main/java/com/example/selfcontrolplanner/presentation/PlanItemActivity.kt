package com.example.selfcontrolplanner.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem
import com.google.android.material.textfield.TextInputLayout

class PlanItemActivity : AppCompatActivity() {
    private lateinit var viewModel: PlanItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var saveButton: Button
    private var screenMode = MODE_UNKNOWN
    private var planItemId = PlannerItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[PlanItemViewModel::class.java]
        initViews()
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {

    }

    private fun launchAddMode() {

    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_PLAN_ITEM_ID)) {
                throw RuntimeException("Param plan item id is absent")
            }
            planItemId = intent.getIntExtra(EXTRA_PLAN_ITEM_ID, PlannerItem.UNDEFINED_ID)
        }
}

private fun initViews() {
    tilName = findViewById(R.id.til_name)
    tilCount = findViewById(R.id.til_count)
    etCount = findViewById(R.id.et_count)
    etName = findViewById(R.id.et_name)
    saveButton = findViewById(R.id.save_button)
}

companion object {

    private const val EXTRA_SCREEN_MODE = "extra_mode"
    private const val EXTRA_PLAN_ITEM_ID = "extra_plan_item_id"
    private const val MODE_EDIT = "mode_edit"
    private const val MODE_ADD = "mode_add"
    private const val MODE_UNKNOWN = ""

    fun newIntentAddItem(context: Context): Intent {
        val intent = Intent(context, PlanItemActivity::class.java)
        intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
        return intent
    }

    fun newIntentEditItem(context: Context, planItemId: Int): Intent {
        val intent = Intent(context, PlanItemActivity::class.java)
        intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
        intent.putExtra(EXTRA_PLAN_ITEM_ID, planItemId)
        return intent
    }
}
}