package ru.mail.aslanisl.quotationsapp.network

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import ru.mail.aslanisl.quotationsapp.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Ivan on 05.05.2018.
 */

private const val API_URL = "https://api.hitbtc.com/"
object ApiBuilder {

    fun build(): Api {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor)
        builder.retryOnConnectionFailure(true)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        val client = builder.build()
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create<Api>(Api::class.java)
    }
}