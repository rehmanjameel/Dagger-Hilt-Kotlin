package org.codebase.daggerhilt.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.codebase.daggerhilt.R

@AndroidEntryPoint
class MainFragment constructor(
    private val string: String
    ) : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val TAG = "AppDebug"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "Here is onViewCreated some string: $string")
    }
}