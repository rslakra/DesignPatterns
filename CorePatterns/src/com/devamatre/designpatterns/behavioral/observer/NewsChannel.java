package com.devamatre.designpatterns.behavioral.observer;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:48 PM
 * Version: 1.0.0
 */
public class NewsChannel implements Channel {

    private String news;

    @Override
    public void update(Object news) {
        this.setNews((String) news);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

}