package com.devamatre.designpatterns.behavioral.observer;

import java.util.Observable;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:49 PM
 * Version: 1.0.0
 */
public class NewsAgencyObservable extends Observable {
    private String news;

    public void setNews(String news) {
        this.news = news;
        setChanged();
        notifyObservers(news);
    }
}