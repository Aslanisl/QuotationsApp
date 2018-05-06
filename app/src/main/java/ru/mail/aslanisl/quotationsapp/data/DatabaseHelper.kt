package ru.mail.aslanisl.quotationsapp.data

import io.realm.Realm
import ru.mail.aslanisl.quotationsapp.data.list.SymbolData
import ru.mail.aslanisl.quotationsapp.domain.MainCallback
import ru.mail.aslanisl.quotationsapp.domain.mapper.SymbolMapper
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse

/**
 * Created by Ivan on 05.05.2018.
 */
class DatabaseHelper {
    private val realm = Realm.getDefaultInstance()

    fun saveSymbols(symbolsResponse: SymbolsResponse){
        realm.executeTransaction {
            realm.delete(SymbolData::class.java)
            val items = SymbolMapper.mapSymbolsResponse(symbolsResponse)
            realm.copyToRealm(items)
        }
    }

    fun loadSymbols(dataCallback: MainCallback<SymbolsResponse>){
        val result = realm.where(SymbolData::class.java).findAllAsync()
        result.addChangeListener { symbols, changeSet ->
            if (changeSet != null){
                result.removeAllChangeListeners()
                val symbolsResponse = SymbolMapper.mapSymbolsData(realm.copyFromRealm(result))
                dataCallback.success?.invoke(symbolsResponse)
            }
        }
    }
}