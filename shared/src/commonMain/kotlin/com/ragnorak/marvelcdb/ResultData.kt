package com.ragnorak.marvelcdb

import kotlin.jvm.JvmName

class ResultData<out T> internal constructor(
    @PublishedApi internal val value: Any?
) {

    companion object {
        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")
        @JvmName("success")
        public fun <T> success(value: T): ResultData<T> = ResultData(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")
        @JvmName("failure")
        public fun <T> failure(exception: Throwable): ResultData<T> =
            ResultData(ResultData.Failure(exception))
    }

    class Failure(
        val throwable: Throwable?
    )

    internal fun exceptionOrNull(): Throwable? = when (value) {
        is ResultData.Failure -> value.throwable
        else -> null
    }
}


fun <T> ResultData<T>.onSuccess(action: (value: T) -> Unit): ResultData<T> {
    action(value as T)
    return this
}

fun <T> ResultData<T>.onFailure(action: (exception: Throwable) -> Unit): ResultData<T> {
    exceptionOrNull()?.let { action(it) }
    return this
}

