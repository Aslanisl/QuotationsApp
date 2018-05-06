package ru.mail.aslanisl.quotationsapp.domain

import android.content.Context
import android.content.SharedPreferences
import ru.mail.aslanisl.quotationsapp.App

/**
 * Created by Ivan on 05.05.2018.
 */

private const val KEY_LIST = "QUOTATION_LIST_TIMESTAMP"

object Prefs {

    private val sharedPreference by lazy { init(App.instance) }

    private fun init(context: Context): SharedPreferences {
        val sharedPrefName = context.packageName + "_preferences"
        return context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    }

    fun setListTimestamp(timestamp: String) {
        sharedPreference.edit().putString(KEY_LIST, timestamp).apply()
    }

    fun getListTimestamp(): String? {
        return sharedPreference.getString(KEY_LIST, null)
    }

}