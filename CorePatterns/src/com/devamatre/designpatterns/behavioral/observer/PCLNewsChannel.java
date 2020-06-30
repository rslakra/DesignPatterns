package com.devamatre.designpatterns.behavioral.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:55 PM
 * Version: 1.0.0
 */
public class PCLNewsChannel implements PropertyChangeListener {

    private String news;

    public void propertyChange(PropertyChangeEvent event) {
        this.setNews((String) event.getNewValue());
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}