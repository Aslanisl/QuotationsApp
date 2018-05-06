package ru.mail.aslanisl.quotationsapp.data.list

import io.realm.RealmObject

/**
 * Created by Ivan on 06.05.2018.
 */
open class SymbolData: RealmObject(){
    var symbol: String? = null
    var step: Float? = null
    var lot: Float? = null
    var currency: String? = null
    var commodity: String? = null
    var takeLiquidityRate: Float? = null
    var provideLiquidityRate: Float? = null
}