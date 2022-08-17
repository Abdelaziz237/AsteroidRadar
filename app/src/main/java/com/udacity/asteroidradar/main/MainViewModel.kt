package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.api.ImageApi
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepo
import kotlinx.coroutines.launch

enum class NasaApiFilter(val value: String) { WEEKLY("weekly"), TODAY("day")}
enum class NasaApiStatus{ LOADING, DONE, ERROR}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = AsteroidsRepo(database)

    var asteroids: LiveData<List<Asteroid>> = repository.asteroids

    private val _status = MutableLiveData<NasaApiStatus>()
    val status: LiveData<NasaApiStatus>
        get() = _status

    private val _image = MutableLiveData<PictureOfDay>()
    val image: LiveData<PictureOfDay>
        get() = _image

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()
    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid

    init {
        _status.value = NasaApiStatus.LOADING
        viewModelScope.launch{
            try {
                getImage()
                repository.refreshAsteroids(NasaApiFilter.WEEKLY)
                _status.value = NasaApiStatus.DONE
            }catch (e: Exception){
                _status.value = NasaApiStatus.ERROR
            }
        }

    }

    fun updateFilter(filter: NasaApiFilter) {
        _status.value = NasaApiStatus.LOADING
        viewModelScope.launch {
            try {
                getImage()
                repository.refreshAsteroids(filter)
                _status.value = NasaApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NasaApiStatus.ERROR
            }
        }
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    private suspend fun getImage(){
        _image.value = ImageApi.retrofitImageService.getImage()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
//
//    fun online(context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities =
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                return true
//            }
//        }
//        return false
//    }
}
