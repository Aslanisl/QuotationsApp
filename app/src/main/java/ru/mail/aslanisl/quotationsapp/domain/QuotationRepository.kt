package ru.mail.aslanisl.quotationsapp.domain

import ru.mail.aslanisl.quotationsapp.data.QuotationCallback
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse

/**
 * Created by Ivan on 05.05.2018.
 */
interface QuotationRepository {

    fun loadItems(callback: QuotationCallback<SymbolsResponse>?)

    fun loadItem(id: Int)

    fun loadItemHistory()
}