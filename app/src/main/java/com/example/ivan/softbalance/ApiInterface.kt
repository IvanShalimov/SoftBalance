package com.example.ivan.softbalance

import com.example.ivan.softbalance.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/forecast")
    fun search(@Query("q") city: String,@Query("mode")mode:String,
               @Query("APPID") APPID:String):Observable<Response>
}