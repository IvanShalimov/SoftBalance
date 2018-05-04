package com.example.ivan.softbalance.presenter

import com.example.ivan.softbalance.model.Response
import com.example.ivan.softbalance.model.WeatherItem

class Parser {

    fun parse(response: Response):ArrayList<WeatherItem>{
        val returnList:ArrayList<WeatherItem> = ArrayList()
        val iterate = response.list.listIterator()
        while (iterate.hasNext()) {
            val item  = iterate.next()
            returnList.add(WeatherItem(item.dt_txt,"${(item.main.temp - 273).toInt()} C"))
        }

        return returnList
    }
}