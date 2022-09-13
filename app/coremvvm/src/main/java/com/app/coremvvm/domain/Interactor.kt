package com.app.coremvvm.domain

import com.app.coremvvm.core.Dispatchers
import com.app.coremvvm.data.HandleError
import java.lang.Exception

interface Interactor {

    suspend fun <T> handle(
        successful: suspend (T) -> Unit,
        atFinish: suspend () -> Unit,
        block: suspend () -> T
    )

    abstract class Abstract(
        private val dispatchers: Dispatchers,
        private val handleError: HandleError
    ) : Interactor {

        override suspend fun <T> handle(
            successful: suspend (T) -> Unit,
            atFinish: suspend () -> Unit,
            block: suspend () -> T
        ) {
            try {
                val result = block.invoke()
                dispatchers.changeToUI { successful.invoke(result) }
            } catch (error: Exception) {
                handleError.handle(error)
            } finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }
        }

    }

}