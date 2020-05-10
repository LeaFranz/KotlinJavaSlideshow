package com.mobappdev.javaslideshow.service;

import android.util.Log;

import com.mobappdev.javaslideshow.Observer.ISlideShow;
import com.mobappdev.javaslideshow.Observer.SlideChangeListener;
import com.mobappdev.javaslideshow.model.Slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Slideshow implements ISlideShow {

    private List<Slide> slides = new ArrayList<>();
    public String sortDirection = "asc";
    private static Slideshow slideshow;
    private SlideChangeListener listener;

    public static Slideshow getInstance(){
        if(Slideshow.slideshow == null){
            Slideshow.slideshow = new Slideshow();
        }
        return Slideshow.slideshow;
    }

    public void collectSlidecounter(){
        notifyObservers();
    }

    public void addSlide(Slide newSlide){
        slides.add(newSlide);
    }

    public void shuffle(){
        Collections.shuffle(slides);
    }

    public Slide next(int counter){

        return slides.get(counter);
    }

    public int getTotalSlides(){
        return slides.size();
    }

    public void sort(){
        if(sortDirection.equalsIgnoreCase("desc")){
            Collections.reverse(slides);
            sortDirection="asc";
        }else if(sortDirection.equalsIgnoreCase("asc")){
            Collections.sort(slides, new SlideComperator());
            sortDirection = "desc";
        }
    }

    @Override
    public String toString (){
        return "";
    }

    @Override
    public void attach(SlideChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void remove(SlideChangeListener listener) {
        this.listener = null;
    }

    @Override
    public void notifyObservers() {
        listener.slideDataChanged();
    }

    public class SlideComperator implements Comparator<Slide>{
    @Override
    public int compare(Slide o1, Slide o2) {
        int value1 = o1.getTimestamp().compareTo(o2.getTimestamp());
        if (value1 == 0) {
            int value2 = o1.getTitle().compareTo(o2.getTitle());
            if (value2 == 0) {
                return o1.getId() - o2.getId();
            } else {
                return value2;
            }
        }
        return value1;
    }
}

}
