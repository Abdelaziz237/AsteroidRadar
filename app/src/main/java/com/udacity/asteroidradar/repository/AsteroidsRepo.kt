package com.udacity.asteroidradar.repository

import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.AsteroidsApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.asDatabaseModel
import com.udacity.asteroidradar.data.asDomainModel
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.main.NasaApiFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AsteroidsRepo(private val database: AsteroidsDatabase) {

    val asteroids = Transformations.map(database.asteroidDao.getAsteroids()) {
        it.asDomainModel()
    }

    suspend fun refreshAsteroids(filter: NasaApiFilter){
        val calendar = Calendar.getInstance()
        val currentTime: Date = calendar.time
        withContext(Dispatchers.IO){
            when(filter) {
                NasaApiFilter.WEEKLY -> {
                    calendar.add(Calendar.DAY_OF_YEAR, 7)
                    val asteroidsList =
                        parseAsteroidsJsonResult(
                            JSONObject(AsteroidsApi.retrofitService.getAsteroids(format(currentTime), format(calendar.time)))
                        )
                    database.asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
                }
                else -> {
                    val asteroidsList =
                        parseAsteroidsJsonResult(
                            JSONObject(AsteroidsApi.retrofitService.getAsteroids(format(currentTime), format(currentTime)))
                        )
                    database.asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
                }
            }
        }
    }

    suspend fun delete(){
        withContext(Dispatchers.IO){
            database.asteroidDao.deleteAll()
        }
    }

    private fun format(date: Date): String{
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }
}