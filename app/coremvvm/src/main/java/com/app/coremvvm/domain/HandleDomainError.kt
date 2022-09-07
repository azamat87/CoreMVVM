package com.app.coremvvm.domain

import com.app.coremvvm.data.HandleError
import java.net.UnknownHostException

class HandleDomainError : HandleError {

    override fun handle(error: Exception): Exception =
        if (error is UnknownHostException)
            NoInternetConnectionException()
         else
            ServiceUnavailableException()

}