package ru.mail.aslanisl.quotationsapp.di.module

import dagger.Module
import dagger.Provides
import ru.mail.aslanisl.quotationsapp.data.DatabaseHelper
import ru.mail.aslanisl.quotationsapp.domain.QuotationRepository
import ru.mail.aslanisl.quotationsapp.domain.QuotationRepositoryImpl
import ru.mail.aslanisl.quotationsapp.network.Api
import ru.mail.aslanisl.quotationsapp.network.ApiBuilder
import javax.inject.Singleton

/**
 * Created by Ivan on 05.05.2018.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi() = ApiBuilder.build()

    @Singleton
    @Provides
    fun provideDatabaseHelper() = DatabaseHelper()

    @Singleton
    @Provides
    fun provideQuotationRepository(api: Api, databaseHelper: DatabaseHelper): QuotationRepository = QuotationRepositoryImpl(api, databaseHelper)
}