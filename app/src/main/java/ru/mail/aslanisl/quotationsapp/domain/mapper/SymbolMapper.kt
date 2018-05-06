package ru.mail.aslanisl.quotationsapp.domain.mapper

import ru.mail.aslanisl.quotationsapp.data.list.SymbolData
import ru.mail.aslanisl.quotationsapp.network.model.SymbolModel
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse

/**
 * Created by Ivan on 06.05.2018.
 */
object SymbolMapper {

    fun mapSymbolsResponse(symbolsResponse: SymbolsResponse): List<SymbolData> {
        val items = mutableListOf<SymbolData>()
        symbolsResponse.symbols?.forEach { symbolModel ->
            items.add(SymbolData().apply {
                symbol = symbolModel.symbol
                step = symbolModel.step
                lot = symbolModel.lot
                currency = symbolModel.currency
                commodity = symbolModel.commodity
                takeLiquidityRate = symbolModel.takeLiquidityRate
                provideLiquidityRate = symbolModel.provideLiquidityRate
            })
        }
        return items
    }

    fun mapSymbolsData(symbols: List<SymbolData>): SymbolsResponse {
        val items = mutableListOf<SymbolModel>()
        symbols.forEach {
            items.add(SymbolModel(
                    it.symbol ?: "",
                    it.step ?: 0f,
                    it.lot ?: 0f,
                    it.currency ?: "",
                    it.commodity ?: "",
                    it.takeLiquidityRate ?: 0f,
                    it.provideLiquidityRate ?: 0f
            ))
        }
        return SymbolsResponse(items)
    }
}