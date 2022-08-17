package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.main.NasaApiStatus


@BindingAdapter("setList")
fun bindList(recyclerView: RecyclerView, dataList: List<Asteroid>?){
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(dataList)
}

@BindingAdapter("loadImage")
fun bindImage(imageView: ImageView, image: PictureOfDay?){
    if (image?.mediaType == "image"){
        Picasso.get()
            .load(image.url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.placeholder_picture_of_day)
            .into(imageView)
    }else{
        Picasso.get()
            .load("https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg")
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.placeholder_picture_of_day)
            .into(imageView)
    }
}

@BindingAdapter("nasaApiStatus")
fun bindStatus(progressBar: ProgressBar, status: NasaApiStatus?) {
    when(status){
        NasaApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        NasaApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        else -> {
            progressBar.visibility = View.GONE
            Toast.makeText(progressBar.context, "CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_LONG).show()
        }
    }
}

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
