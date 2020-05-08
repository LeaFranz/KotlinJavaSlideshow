package com.mobappdev.javaslideshow.service;

import com.mobappdev.javaslideshow.model.Slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Slideshow{

    private List<Slide> slides = new ArrayList<>();
    private String sortDirection = "asc";
    private static Slideshow slideshow;

    public static Slideshow getInstance(){
        if(Slideshow.slideshow == null){
            Slideshow.slideshow = new Slideshow();
        }
        return Slideshow.slideshow;
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
            Collections.sort(slides, Collections.<Slide>reverseOrder());
        }else if(sortDirection.equalsIgnoreCase("asc")){
            Collections.sort(slides, new SlideComperator());
        }
    }

    @Override
    public String toString (){
        return "";
    }

public class SlideComperator implements Comparator<Slide>{
    @Override
    public int compare(Slide o1, Slide o2) {
        int value1 = o1.getTimestamp() - o2.getTimestamp();
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
