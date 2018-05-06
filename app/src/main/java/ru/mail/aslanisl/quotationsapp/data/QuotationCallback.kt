package ru.mail.aslanisl.quotationsapp.data

/**
 * Created by Ivan on 05.05.2018.
 */
class QuotationCallback<T> {
    var preExecute: (() -> Unit)? = null
    var success: ((T) -> Unit)? = null
    var failure: ((String?) -> Unit)? = null
}