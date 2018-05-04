package com.example.ivan.softbalance.ui

import android.os.Bundle
import android.os.Parcelable
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState

class WeatherViewState:RestorableViewState<WeatherView> {

    companion object {
        const val STATE = "state"
        const val DATA = "data"
    }

    var data:ArrayList<WeatherItem>? = null
    var state:Parcelable? = null


    override fun apply(view: WeatherView?, retained: Boolean) {

    }

    override fun saveInstanceState(out: Bundle) {
        out.putParcelable(STATE,state)
        out.putParcelableArrayList(DATA,data)
    }

    fun saveScreenData(list:ArrayList<WeatherItem>,state:Parcelable){
        data = list
        this.state = state
    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<WeatherView> {
        data = `in`?.getParcelableArrayList(DATA)
        state = `in`?.getParcelable(STATE)
        return this
    }
}