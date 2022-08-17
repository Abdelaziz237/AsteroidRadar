package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val AsteroidsRequest = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

private val ImageRequest = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface AsteroidApiService{
    @GET("neo/rest/v1/feed?api_key=KyZ1qYhttJ0glFgCJvqgapcyN6G7vUYuXfbHesj1")
    suspend fun getAsteroids(@Query("start_date") start: String, @Query("end_date") end: String) : String
}

interface ImageApiService{
    @GET("planetary/apod?api_key=KyZ1qYhttJ0glFgCJvqgapcyN6G7vUYuXfbHesj1")
    suspend fun getImage() : PictureOfDay
}

object AsteroidsApi {
    val retrofitService: AsteroidApiService by lazy {
        AsteroidsRequest.create(AsteroidApiService::class.java)
    }
}

object ImageApi {
    val retrofitImageService: ImageApiService by lazy {
        ImageRequest.create(ImageApiService::class.java)
    }
}