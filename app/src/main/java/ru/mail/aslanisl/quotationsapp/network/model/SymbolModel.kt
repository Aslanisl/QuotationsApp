package ru.mail.aslanisl.quotationsapp.network.model

/**
 * Created by Ivan on 06.05.2018.
 */
data class SymbolModel(
        val symbol: String,
        val step: Float,
        val lot: Float,
        val currency: String,
        val commodity: String,
        val takeLiquidityRate: Float,
        val provideLiquidityRate: Float
)