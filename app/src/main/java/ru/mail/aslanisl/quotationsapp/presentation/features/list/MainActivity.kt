package ru.mail.aslanisl.quotationsapp.presentation.features.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.mail.aslanisl.quotationsapp.R
import ru.mail.aslanisl.quotationsapp.data.QuotationCallback
import ru.mail.aslanisl.quotationsapp.di.component.AppComponent
import ru.mail.aslanisl.quotationsapp.domain.repository.QuotationRepository
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse
import ru.mail.aslanisl.quotationsapp.presentation.base.BaseActivity
import ru.mail.aslanisl.quotationsapp.presentation.features.details.DetailsActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var repository: QuotationRepository

    private var callback: QuotationCallback<SymbolsResponse>? = null

    override fun injectDi(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quotation_list.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter {
            DetailsActivity.startDetailsActivity(this, it)
        }
        quotation_list.adapter = adapter

        callback = QuotationCallback<SymbolsResponse>().apply {
            preExecute = {
                quotation_progress.visibility = View.VISIBLE
                quotation_list.visibility = View.GONE
            }
            success = {
                quotation_progress.visibility = View.GONE
                quotation_list.visibility = View.VISIBLE
                adapter.updateList(it.symbols)
            }
        }
        repository.loadItems(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }
}
