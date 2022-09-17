package com.app.coremvvm.sl

import androidx.lifecycle.ViewModel

interface Module<T : ViewModel> {

    fun viewModel() : T
}