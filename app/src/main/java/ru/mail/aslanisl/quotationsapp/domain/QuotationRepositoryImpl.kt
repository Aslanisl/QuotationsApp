package ru.mail.aslanisl.quotationsapp.domain

import ru.mail.aslanisl.quotationsapp.data.DatabaseHelper
import ru.mail.aslanisl.quotationsapp.data.QuotationCallback
import ru.mail.aslanisl.quotationsapp.network.Api
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse
import java.util.*

/**
 * Created by Ivan on 05.05.2018.
 */
class QuotationRepositoryImpl(
        private val api: Api,
        private val databaseHelper: DatabaseHelper
) : QuotationRepository {

    override fun loadItems(callback: QuotationCallback<SymbolsResponse>?) {
        callback?.preExecute?.invoke()
        val needToLoad = Prefs.getListTimestamp() == null

        val newCallback = MainCallback<SymbolsResponse>().apply {
            success = {
                Prefs.setListTimestamp(Calendar.getInstance().toString())
                databaseHelper.saveSymbols(it)
                callback?.success?.invoke(it)
            }
            failure = { callback?.failure?.invoke(it) }
        }

        callback?.preExecute?.invoke()

        when (needToLoad) {
            true -> {
                api.loadItems().enqueue(newCallback)
            }

            false -> {
                databaseHelper.loadSymbols(newCallback)
            }
        }
    }

    override fun loadItem(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadItemHistory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}