package ru.mail.aslanisl.quotationsapp.domain

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ivan on 06.05.2018.
 */
class MainCallback<T> : Callback<T> {

    var preExecute: (() -> Unit)? = null
    var success: ((T) -> Unit)? = null
    var failure: ((String?) -> Unit)? = null

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        failure?.invoke(t?.message)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        val responseBody = response?.body()

        when (responseBody) {
            null -> failure?.invoke(null)

            else -> success?.invoke(responseBody)
        }
    }
}