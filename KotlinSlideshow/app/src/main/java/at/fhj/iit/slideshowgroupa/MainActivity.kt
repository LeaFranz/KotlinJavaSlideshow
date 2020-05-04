package at.fhj.iit.slideshowgroupa

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import at.fhj.iit.slideshowgroupa.model.Slide
import at.fhj.iit.slideshowgroupa.service.Slideshow
import java.time.LocalDate
import kotlin.properties.Delegates
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    //SensorEventListener

    private val holidaySlides = Slideshow
    private var slideImgView: ImageView? = null
    private var totalSlides = 0
    private var gpsText: TextView? = null
    private val maxSlides: Int = 4

    //observable
    private var currentSlideId: Int by Delegates.observable(0) { prop, oldSlideId, newSlideId ->
        var slideNumber = newSlideId
        slideNumber++
        findViewById<TextView>(R.id.slideNo).text = "Slide $slideNumber of $totalSlides"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalSlides = holidaySlides.getTotalSlides()
        val sharedPreference = SharedPreference(this)

        if (totalSlides != maxSlides) {
            addSomeDemoSlides()
            totalSlides = holidaySlides.getTotalSlides()
        }

        currentSlideId = sharedPreference.getValueInt("slideId")
        val firstSlide = holidaySlides.next(currentSlideId)

        updateUIWithSlide(firstSlide)
        slideImgView?.setOnClickListener {
            showNextSlide()
            sharedPreference.save("slideId", currentSlideId)
        }

        val shuffleButton = findViewById<Button>(R.id.shuffle)
        shuffleButton.setOnClickListener {
            holidaySlides.shuffle()
        }

        val sortButton = findViewById<Button>(R.id.sortButton)
        sortButton.setOnClickListener {
            holidaySlides.sort()
            val slide = holidaySlides.next(currentSlideId)
            updateUIWithSlide(slide)
            if (holidaySlides.sortDirection == "asc") {
                sortButton.text = "SORT ASC"
            } else {
                sortButton.text = "SORT DESC"
            }
        }
    }

    private fun showNextSlide() {
        currentSlideId++
        if (currentSlideId == totalSlides) currentSlideId = 0 else currentSlideId
        val nextSlide = holidaySlides.next(currentSlideId)
        updateUIWithSlide(nextSlide)
    }

    private fun updateUIWithSlide(slide: Slide) {
        val slideTitle = findViewById<TextView>(R.id.slideTitle)
        val description = findViewById<TextView>(R.id.description)
        val timestamp = findViewById<TextView>(R.id.timestamp)

        slideTitle.text = slide.title
        slideImgView = findViewById<ImageView>(R.id.slideImage)
        val imgResId = resources.getIdentifier(
            slide.imageName,
            "drawable",
            packageName
        )
        slideImgView?.setImageResource(imgResId)
        description.text = slide.description
        gpsText = findViewById<TextView>(R.id.gpsText)
        gpsText?.text = slide.GPS
        timestamp.text = (slide.timestamp).toString()
    }


    private fun addSomeDemoSlides() {
        holidaySlides.addSlide(
            Slide(
                1,
                "More Coffee!!",
                "coffee",
                "Hawaii",
                LocalDate.parse("2019-07-29"),
                "This was my coffee."
            )
        )
        holidaySlides.addSlide(
            Slide(
                2,
                "Beautiful sunset",
                "sunset",
                "Hawaii",
                LocalDate.parse("2019-07-29"),
                "This was a beautiful sunset."
            )
        )
        holidaySlides.addSlide(
            Slide(
                3,
                "Enjoy the beach",
                "beach",
                "Hawaii",
                LocalDate.parse("2019-07-24"),
                "This was a nice beach."
            )
        )
        holidaySlides.addSlide(
            Slide(
                4,
                "Sand and more",
                "sand",
                "Hawaii",
                LocalDate.parse("2019-07-25"),
                "This sand was very hot."
            )
        )
    }
}
