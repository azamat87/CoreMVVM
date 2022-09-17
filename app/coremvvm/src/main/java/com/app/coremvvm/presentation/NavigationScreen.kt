package com.app.coremvvm.presentation

import androidx.fragment.app.FragmentManager
import com.app.coremvvm.core.Matches

abstract class NavigationScreen(
    private val id: String,
    private val clazz: Class<out BaseFragment<*>>,
    private val strategy: ShowStrategy
) : Matches<NavigationScreen>, ShowScreen {

    override fun matches(data: NavigationScreen): Boolean = data.id == this.id

    override fun toString(): String = "id $id"

    override fun show(containerId: Int, fragmentManager: FragmentManager) {
        when (strategy) {
            ShowStrategy.REPLACE -> fragmentManager.beginTransaction()
                .replace(containerId, clazz.newInstance())
                .commit()

            ShowStrategy.ADD -> fragmentManager.beginTransaction()
                .add(containerId, clazz.newInstance())
                .addToBackStack(id)
                .commit()

            ShowStrategy.POPUP -> fragmentManager.popBackStack()
        }
    }

}
