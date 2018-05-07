package ru.mail.aslanisl.quotationsapp.domain.repository

import ru.mail.aslanisl.quotationsapp.data.DatabaseHelper
import ru.mail.aslanisl.quotationsapp.data.Prefs
import ru.mail.aslanisl.quotationsapp.data.QuotationCallback
import ru.mail.aslanisl.quotationsapp.domain.MainCallback
import ru.mail.aslanisl.quotationsapp.network.Api
import ru.mail.aslanisl.quotationsapp.network.model.SymbolModel
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse
import ru.mail.aslanisl.quotationsapp.utils.WeakRef
import java.util.*

/**
 * Created by Ivan on 05.05.2018.
 */
private const val INTERVAL_TIMESTAMP_MILLIS = 24 * 60 * 60 * 1000L

class QuotationRepositoryImpl(
        private val api: Api,
        private val databaseHelper: DatabaseHelper
) : QuotationRepository {

    private var symbolsResponse: SymbolsResponse? = null
    private var symbolsCallback by WeakRef<QuotationCallback<SymbolsResponse>?>()
    private var symbolsLoading = false

    override fun loadItems(callback: QuotationCallback<SymbolsResponse>?) {
        symbolsCallback = callback
        if (symbolsLoading) return

        val symbolsResponse = symbolsResponse
        if (symbolsResponse != null) {
            symbolsCallback?.success?.invoke(symbolsResponse)
            return
        }

        symbolsCallback?.preExecute?.invoke()

        val needToLoad = isNeedToLoadSymbols()

        val mainCallback = MainCallback<SymbolsResponse>().apply {
            success = {
                symbolsLoading = false
                Prefs.setListTimestamp(Calendar.getInstance().timeInMillis.toString())
                databaseHelper.saveSymbols(it)
                symbolsCallback?.success?.invoke(it)
                this@QuotationRepositoryImpl.symbolsResponse = it
            }
            failure = {
                symbolsLoading = false
                symbolsCallback?.failure?.invoke(it)
            }
        }

        symbolsCallback?.preExecute?.invoke()

        when (needToLoad) {
            true -> {
                api.loadItems().enqueue(mainCallback)
            }

            false -> {
                databaseHelper.loadSymbols(mainCallback)
            }
        }
    }

    private fun isNeedToLoadSymbols(): Boolean {
        val timestamp = try {
            Prefs.getListTimestamp()?.toLong()
        } catch (e: Exception){
            null
        }
        return timestamp == null
                || Calendar.getInstance().timeInMillis > timestamp + INTERVAL_TIMESTAMP_MILLIS
    }

    override fun loadItem(id: String?): SymbolModel? {
        if (id == null) return null
        return symbolsResponse?.symbols?.firstOrNull { it.symbol == id }
    }
}