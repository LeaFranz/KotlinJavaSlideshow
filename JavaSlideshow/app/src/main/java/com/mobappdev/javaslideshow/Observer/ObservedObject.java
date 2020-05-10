package com.mobappdev.javaslideshow.Observer;

import java.util.Observable;

public class ObservedObject extends Observable {
    private int slideId;

    public ObservedObject(int value) {
        slideId = value;
    }

    public void setValue(int value) {
        // if value has changed notify observers
        if(slideId!=value) {
            slideId = value;
            // mark as value changed
            setChanged();
        }
    }
}