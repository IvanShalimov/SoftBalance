package com.example.ivan.softbalance

import com.example.ivan.softbalance.model.Response
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("data/2.5/forecast?q={city},us&mode=json&APPID=ad0a54100d47d15b572d18c42c9e7ef4")
    fun search(@Path("city") city: String):Observable<Call<Response>>
}