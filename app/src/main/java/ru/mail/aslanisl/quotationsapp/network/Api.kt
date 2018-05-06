package ru.mail.aslanisl.quotationsapp.network

import retrofit2.Call
import retrofit2.http.GET
import ru.mail.aslanisl.quotationsapp.network.model.SymbolsResponse

/**
 * Created by Ivan on 05.05.2018.
 */
interface Api {

    @GET("/api/1/public/symbols")
    fun loadItems(): Call<SymbolsResponse>
}