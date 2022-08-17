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
<img src="screenshots/screen_1.png" width="400" height="800" /> | <img src="screenshots/screen_2.png" width="400" height="800" />

<img src="screenshots/screen_3.png" width="400" height="800" /> | <img src="screenshots/screen_4.png" width="400" height="800" />


## Dependencies
```
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.core:core-ktx:1.2.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

// Download and parse data
implementation "com.squareup.moshi:moshi:1.8.0"
implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
implementation "com.squareup.retrofit2:retrofit:2.6.2"
implementation "com.squareup.retrofit2:converter-moshi:2.5.0"
implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'

// Kotlin coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

implementation "androidx.recyclerview:recyclerview:1.1.0"

// Image downloader
implementation 'com.squareup.picasso:picasso:2.5.2'

implementation "androidx.room:room-runtime:2.2.3"
kapt "androidx.room:room-compiler:2.2.3"

implementation "android.arch.work:work-runtime-ktx:1.0.1"

testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
```
