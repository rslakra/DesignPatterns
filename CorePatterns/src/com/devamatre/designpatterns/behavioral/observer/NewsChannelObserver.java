package com.devamatre.designpatterns.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:50 PM
 * Version: 1.0.0
 */
public class NewsChannelObserver implements Observer {

    private String news;

    @Override
    public void update(Observable observable, Object news) {
        this.setNews((String) news);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}