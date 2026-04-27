package com.dshatz.collapsingtoolbar.compat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dshatz.collapsingtoolbar.sample.android.R

class CompatActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compat)

        val composeFragment = CompatFragment()
        supportFragmentManager.beginTransaction().add(R.id.contentFrameLayout, composeFragment).commit()
    }
}