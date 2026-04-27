package com.dshatz.collapsingtoolbar.compat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.dshatz.collapsingtoolbar.Root
import com.dshatz.collapsingtoolbar.sample.android.R

class CompatFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.compose, container, false)
        (view as ComposeView).setContent {
            Root()
        }
        return view
    }
}