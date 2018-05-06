package ru.mail.aslanisl.quotationsapp.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.mail.aslanisl.quotationsapp.App
import ru.mail.aslanisl.quotationsapp.di.component.AppComponent

/**
 * Created by Ivan on 05.05.2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun injectDi(appComponent: AppComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDi(App.appComponent)
        super.onCreate(savedInstanceState)
    }
}