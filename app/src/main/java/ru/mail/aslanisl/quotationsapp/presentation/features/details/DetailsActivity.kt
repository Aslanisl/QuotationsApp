package ru.mail.aslanisl.quotationsapp.presentation.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quotation_details.*
import ru.mail.aslanisl.quotationsapp.R
import ru.mail.aslanisl.quotationsapp.di.component.AppComponent
import ru.mail.aslanisl.quotationsapp.domain.repository.QuotationRepository
import ru.mail.aslanisl.quotationsapp.network.model.SymbolModel
import ru.mail.aslanisl.quotationsapp.presentation.base.BaseActivity
import javax.inject.Inject

private const val ITEM_ID = "item_id"
class DetailsActivity : BaseActivity() {

    @Inject lateinit var repository: QuotationRepository

    override fun injectDi(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    companion object {
        fun startDetailsActivity(context: Context, itemId: String){
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ITEM_ID, itemId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotation_details)

        val symbolId = intent?.getStringExtra(ITEM_ID)
        val symbol = repository.loadItem(symbolId)
        if (symbol == null) {
            finish()
            return
        }
        initSymbol(symbol)
    }

    private fun initSymbol(symbolModel: SymbolModel){
        details_symbol_title.text = "Symbol name: ${symbolModel.symbol}"
        details_symbol_step.text = "Price step parameter: ${symbolModel.step}"
        details_symbol_lot.text = "Lot size parameter: ${symbolModel.lot}"
        details_symbol_currency.text = "Value of this symbol: ${symbolModel.currency}"
        details_symbol_commodity.text = "Second value of this symbol: ${symbolModel.commodity}"
        details_symbol_takeLiquidityRate.text = "Liquidity taker fee: ${symbolModel.takeLiquidityRate}"
        details_symbol_provideLiquidityRate.text = "Liquidity provider rebate: ${symbolModel.provideLiquidityRate}"
    }
}
