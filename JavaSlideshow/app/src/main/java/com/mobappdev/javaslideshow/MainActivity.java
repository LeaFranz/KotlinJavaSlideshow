package com.mobappdev.javaslideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobappdev.javaslideshow.Observer.ObservedObject;
import com.mobappdev.javaslideshow.Observer.SlideLogger;
import com.mobappdev.javaslideshow.service.Slideshow;
import com.mobappdev.javaslideshow.model.Slide;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    //TODO: observable
    public int currentSlideId = 0;
    public MainActivity(int id) {
        this.currentSlideId = id;
    }

    public MainActivity(){

    }

    private Slideshow holidaySlides = Slideshow.getInstance();
    private SlideLogger logger = new SlideLogger();
    private int totalSlides = 0;
    private int maxSlides = 4;
    private ImageView imageView;
    SharedPref sharedP = new SharedPref();
    ObservedObject watched;
    MainActivity watcher;
    TextView slideNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create watched and watcher objects
        watched = new ObservedObject(0);
        // watcher object listens to object change
        watcher = new MainActivity(0);
        watched.addObserver(watcher);

        //difference: i have to manually get the context for the shared prefs
        final Context context = getApplicationContext();
        totalSlides = holidaySlides.getTotalSlides();
        slideNo = (TextView)findViewById(R.id.slideNo);

        if(totalSlides != maxSlides){
            addDemoSlides();
            totalSlides = holidaySlides.getTotalSlides();
        }

        currentSlideId = sharedP.getValueInt(context, "slideId");
        Slide firstSlide = holidaySlides.next(currentSlideId);
        updateUI(firstSlide);
        //difference: null check, more complicated on click listener
        if(imageView != null){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextSlide();
                    sharedP.save(context, "slideId", currentSlideId);
                }
            });
        }

        Button shuffleButton = (Button) findViewById((R.id.shuffle));
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holidaySlides.shuffle();
            }
        });

        final Button sortButton = (Button) findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holidaySlides.sort();
                Slide slide = holidaySlides.next(currentSlideId);
                updateUI(slide);
                if(holidaySlides.sortDirection.equals("asc")){
                    sortButton.setText("SORT ASC");
                } else {
                    sortButton.setText("SORT DESC");
                }
            }
        });


    }

    public void showNextSlide(){
        watched.setValue(currentSlideId);
        currentSlideId++;
        if(watched.hasChanged()){
            slideNo.setText("");
        }else{
            slideNo.setText(currentSlideId);
        }
        //difference: longer if
        if(currentSlideId == totalSlides){
            currentSlideId = 0;
        }
        Slide nextSlide = holidaySlides.next(currentSlideId);
        updateUI(nextSlide);

    }

    public void updateUI(Slide slide){
        TextView title = (TextView) findViewById(R.id.slideTitle);
        TextView description = (TextView) findViewById(R.id.description);
        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        TextView gps = (TextView) findViewById(R.id.gpsText);
        int imgResId = getResources().getIdentifier(slide.getImageName(), "drawable", getPackageName());
        imageView = (ImageView) findViewById(R.id.slideImage);

        //difference: using getter!
        title.setText(slide.getTitle());
        description.setText(slide.getDescription());
        timestamp.setText(slide.getTimestamp()+"");
        //difference check for null
        if(gps != null){
            gps.setText(slide.getGps());
        }
        if(imageView != null){
            imageView.setImageResource(imgResId);
        }
    }

    public void addDemoSlides(){
        //difference: create with constructor
        holidaySlides.addSlide(new Slide(1,
                "More Coffee!!",
                "coffee",
                "Hawaii",
                LocalDate.parse("2019-07-24"),
                "This was my coffee."));
        holidaySlides.addSlide(new Slide(2,
                "Beautiful sunset",
                "sunset",
                "Hawaii",
                LocalDate.parse("2019-07-24"),
                "This was a beautiful sunset."));
        holidaySlides.addSlide(new Slide(3,
                "Enjoy the beach",
                "beach",
                "Hawaii",
                LocalDate.parse("2019-07-24"),
                "This was a nice beach."));
        holidaySlides.addSlide(new Slide(4,
                "Sand and more",
                "sand",
                "Hawaii",
                LocalDate.parse("2019-07-25"),
                "This sand was very hot."));

    }


    @Override
    public void update(Observable o, Object arg) {
    }
}
