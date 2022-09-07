package com.app.coremvvm.data

interface HandleError {

    fun handle(error: Exception): Exception

}
