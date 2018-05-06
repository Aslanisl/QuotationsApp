package ru.mail.aslanisl.quotationsapp.presentation.features

import android.os.Bundle
import ru.mail.aslanisl.quotationsapp.R
import ru.mail.aslanisl.quotationsapp.di.component.AppComponent
import ru.mail.aslanisl.quotationsapp.presentation.base.BaseActivity

class HistoryActivity : BaseActivity() {

    override fun injectDi(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }
}
