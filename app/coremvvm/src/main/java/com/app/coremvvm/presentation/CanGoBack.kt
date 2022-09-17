package com.app.coremvvm.presentation

interface CanGoBack {

    fun canGoBack(): Boolean

    class Empty : CanGoBack {
        override fun canGoBack(): Boolean = true
    }

    interface Callback : CanGoBack {

        fun updateCallback(canGoBack: CanGoBack)

        class Base : Callback {

            private var canGoBack: CanGoBack = Empty()

            override fun updateCallback(canGoBack: CanGoBack) {
                this.canGoBack = canGoBack
            }

            override fun canGoBack(): Boolean = canGoBack.canGoBack()
        }
    }

}