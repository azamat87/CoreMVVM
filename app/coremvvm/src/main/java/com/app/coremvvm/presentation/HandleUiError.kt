package com.app.coremvvm.presentation

import android.content.Intent
import androidx.annotation.StringRes
import com.app.coremvvm.R
import com.app.coremvvm.core.ManageResources
import com.app.coremvvm.data.HandleError
import com.app.coremvvm.domain.NoInternetConnectionException
import com.app.coremvvm.domain.ServiceUnavailableException

abstract class HandleUiErrorAbstract(
    private val manageResources: ManageResources,
    private val globalErrorCommunication: GlobalErrorCommunication.Update,
    private val handleError: HandleError = HandleGenericUiError(manageResources, globalErrorCommunication),
) : HandleError {

    @StringRes
    protected open val noConnectionExceptionMessage: Int = R.string.connection_error

    @StringRes
    protected open val serviceUnavailableExceptionMessage: Int = R.string.error_1

    override fun handle(error: Exception): Exception {
        Intent
        val messageId = when (error) {
            is NoInternetConnectionException -> noConnectionExceptionMessage
            is ServiceUnavailableException -> serviceUnavailableExceptionMessage
            else -> 0
        }
        return if (messageId == 0) {
            handleError.handle(error)
        } else {
            globalErrorCommunication.map(manageResources.string(messageId))
            error
        }
    }

}

class HandleGenericUiError(
    private val manageResources: ManageResources,
    private val globalErrorCommunication: GlobalErrorCommunication.Update
) : HandleError {
    override fun handle(error: Exception): Exception {
        globalErrorCommunication.map(manageResources.string(R.string.error_2))
        return error
    }
}


class HandleUiError(
    manageResources: ManageResources,
    globalErrorCommunication: GlobalErrorCommunication.Update
) : HandleUiErrorAbstract(manageResources, globalErrorCommunication)