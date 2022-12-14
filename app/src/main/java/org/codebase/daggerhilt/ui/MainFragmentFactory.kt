package org.codebase.daggerhilt.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainFragmentFactory
@Inject
constructor(
    private val string: String
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            MainFragment::class.java.name -> {
                MainFragment(string)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}