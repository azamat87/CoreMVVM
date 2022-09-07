package com.app.coremvvm.core

interface Matches<T> {

    fun matches(data: T) : Boolean

}