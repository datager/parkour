package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    // 此只有query这个参数是需要动态指定的，我们使用@Query注解的方式来进行实现, 另外两个参数是不会变的，因此固定写在@GET注解中即可
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    // 返回值被声明成了Call<PlaceResponse>，这样Retrofit就会将服务器返回的JSON数据自动解析成PlaceResponse对象
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}
