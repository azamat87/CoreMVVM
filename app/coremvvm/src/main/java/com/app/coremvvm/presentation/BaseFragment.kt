package com.app.coremvvm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class AbstractFragment<T: ViewModel>: Fragment() {

    protected lateinit var viewModel: T

    protected abstract fun viewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(viewModelClass(), this)
    }

}

abstract class BaseFragment<T: ViewModel>: AbstractFragment<T>() {

    protected abstract val layoutResId : Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(layoutResId, container, false)

}