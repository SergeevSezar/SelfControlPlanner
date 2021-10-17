package com.example.selfcontrolplanner.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem
import com.google.android.material.textfield.TextInputLayout

class PlanItemActivity : AppCompatActivity() {
//    private lateinit var viewModel: PlanItemViewModel
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var saveButton: Button
    private var screenMode = MODE_UNKNOWN
    private var planItemId = PlannerItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_item)
        parseIntent()
//        viewModel = ViewModelProvider(this)[PlanItemViewModel::class.java]
//        initViews()
//        addChangeTextListeners()
        launchRightMode()
//        observeViewModel()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_cont)
//            } else {
//                null
//            }
//            tilCount.error = message
//        }
//        viewModel.closeScreen.observe(this) {
//            finish()
//        }
//    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> PlanItemFragment.newInstanceEditItem(planItemId)
            MODE_ADD -> PlanItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.plan_item_container, fragment)
            .commit()
    }

//    private fun addChangeTextListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })
//    }
//
//    private fun launchEditMode() {
//        viewModel.getPlanItem(planItemId)
//        viewModel.planItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.data.toString())
//        }
//        saveButton.setOnClickListener {
//            viewModel.editPlanItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    private fun launchAddMode() {
//        saveButton.setOnClickListener {
//            viewModel.addPlanItem(etName.text?.toString(), etCount.text?.toString())
//        }
//    }

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

//    private fun initViews() {
//        tilName = findViewById(R.id.til_name)
//        tilCount = findViewById(R.id.til_count)
//        etCount = findViewById(R.id.et_count)
//        etName = findViewById(R.id.et_name)
//        saveButton = findViewById(R.id.save_button)
//    }

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