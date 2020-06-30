package com.devamatre.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:47 PM
 * Version: 1.0.0
 */
public class NewsAgency {
    private String news;
    private List<Channel> channels = new ArrayList<>();

    public void addObserver(Channel channel) {
        this.channels.add(channel);
    }

    public void removeObserver(Channel channel) {
        this.channels.remove(channel);
    }

    public void setNews(String news) {
        this.news = news;
        for (Channel channel : this.channels) {
            channel.update(this.news);
        }
    }
}