# AsteroidRadar
Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids given a period with data such as the size, velocity, distance to earth and if they are potentially hazardous. 

This app demonstrates the following views and techniques:
* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects 
* [Glide](https://bumptech.github.io/glide/) to load and cache images by URL.
  
It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments
* RecyclerView with DiffUtil

This app also uses  [Room](https://developer.android.com/topic/libraries/architecture/room) and a Repository to create an offline cache.
In addition, it also covers how to use [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) for scheduling periodic work. In this app it's used to setup a weekly background data sync.


## Screenshots
<img src="screenshots/screen_1.png" width="200" height="400" /> | <img src="screenshots/screen_2.png" width="200" height="400" />

<img src="screenshots/screen_3.png" width="200" height="400" /> | <img src="screenshots/screen_4.png" width="200" height="400" />
