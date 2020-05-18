package com.mobappdev.javaslideshow.service;

import android.util.Log;

import com.mobappdev.javaslideshow.model.Slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Slideshow {

    private List<Slide> slides = new ArrayList<>();
    public String sortDirection = "asc";
    private static Slideshow slideshow;

    public static Slideshow getInstance() {
        if (Slideshow.slideshow == null) {
            Slideshow.slideshow = new Slideshow();
        }
        return Slideshow.slideshow;
    }

    public void addSlide(Slide newSlide) {
        slides.add(newSlide);
    }

    public void shuffle() {
        Collections.shuffle(slides);
    }

    public Slide next(int counter){
        if(counter < slides.size()){
            return slides.get(counter);
        }else{
            return slides.get(1);
        }
    }

    public int getTotalSlides() {
        return slides.size();
    }

    public void sort() {
        if (sortDirection.equalsIgnoreCase("desc")) {
            Collections.reverse(slides);
            sortDirection = "asc";
        } else if (sortDirection.equalsIgnoreCase("asc")) {
            Collections.sort(slides, new SlideComparator());
            sortDirection = "desc";
        }
    }

    public class SlideComparator implements Comparator<Slide> {
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
