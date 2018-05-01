package com.example.ivan.softbalance.model



data class Response(
    val cod: String, //200
    val message: Double, //0.0027
    val cnt: Int, //40
    val list: List<X>,
    val city: City
) {
    data class X(
        val dt: Int, //1525456800
        val main: Main,
        val weather: List<Weather>,
        val clouds: Clouds,
        val wind: Wind,
        val rain: Rain,
        val sys: Sys,
        val dt_txt: String //2018-05-04 18:00:00
    ) {
        data class Sys(
            val pod: String //d
        )
        class Rain{

        }
        data class Clouds(
            val all: Int //48
        )
        data class Wind(
            val speed: Double, //1.88
            val deg: Double //289.003
        )
        data class Main(
            val temp: Double, //287.897
            val temp_min: Double, //287.897
            val temp_max: Double, //287.897
            val pressure: Double, //940.69
            val sea_level: Double, //1034.12
            val grnd_level: Double, //940.69
            val humidity: Int, //61
            val temp_kf: Double //0
        )
        data class Weather(
            val id: Int, //802
            val main: String, //Clouds
            val description: String, //scattered clouds
            val icon: String //03d
        )
    }

    data class City(
        val id: Int, //5601538
        val name: String, //Moscow
        val coord: Coord,
        val country: String, //US
        val population: Int //23800
    ) {
        data class Coord(
            val lat: Double, //46.7324
            val lon: Double //-117.0002
        )
    }
}