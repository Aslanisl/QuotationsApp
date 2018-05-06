package ru.mail.aslanisl.quotationsapp.di.component

import dagger.Component
import ru.mail.aslanisl.quotationsapp.di.module.AppModule
import ru.mail.aslanisl.quotationsapp.presentation.base.BaseActivity
import ru.mail.aslanisl.quotationsapp.presentation.features.DetailsActivity
import ru.mail.aslanisl.quotationsapp.presentation.features.HistoryActivity
import ru.mail.aslanisl.quotationsapp.presentation.features.list.MainActivity
import javax.inject.Singleton

/**
 * Created by Ivan on 05.05.2018.
 */
@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)
    fun inject(activity: HistoryActivity)
}