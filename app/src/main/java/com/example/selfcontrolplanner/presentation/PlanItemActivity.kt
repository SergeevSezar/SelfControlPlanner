package com.example.selfcontrolplanner.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.selfcontrolplanner.R
import com.example.selfcontrolplanner.domain.PlannerItem

class PlanItemActivity : AppCompatActivity(), PlanItemFragment.OnEditingFinishedListener {
    private var screenMode = MODE_UNKNOWN
    private var planItemId = PlannerItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    override fun onEditingFinished() {
        finish()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> PlanItemFragment.newInstanceEditItem(planItemId)
            MODE_ADD -> PlanItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.plan_item_container, fragment)
            .commit()
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