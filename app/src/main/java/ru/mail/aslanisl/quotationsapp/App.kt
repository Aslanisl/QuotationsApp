package ru.mail.aslanisl.quotationsapp

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import ru.mail.aslanisl.quotationsapp.di.component.AppComponent
import ru.mail.aslanisl.quotationsapp.di.component.DaggerAppComponent

/**
 * Created by Ivan on 05.05.2018.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
        Fabric.with(this, Crashlytics())

        appComponent = DaggerAppComponent.builder().build()
    }
}