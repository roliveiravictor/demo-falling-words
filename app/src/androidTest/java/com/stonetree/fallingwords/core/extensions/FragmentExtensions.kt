package com.stonetree.fallingwords.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation.setViewNavController
import com.stonetree.fallingwords.R

inline fun <reified F : Fragment> NavController.launchFragmentScenario(
    bundle: Bundle?,
    fragment: F
): FragmentScenario<F> {
    return launchFragmentInContainer(bundle, R.style.Theme_MaterialComponents_Light_DarkActionBar) {
        fragment.also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { lifeCycleOwner ->
                if (lifeCycleOwner != null) {
                    setViewNavController(fragment.requireView(), this)
                }
            }
        }
    }
}

fun <F : Fragment> FragmentScenario<F>.execute(
    function: FragmentScenario<F>.(F) -> Unit
) {
    onFragment { fragment ->
        function(fragment)
    }
}