package com.devamatre.designpatterns.behavioral.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:52 PM
 * Version: 1.0.0
 */
public class PCLNewsAgency {
    private String news;

    private PropertyChangeSupport support;

    public PCLNewsAgency() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener changeListener) {
        support.addPropertyChangeListener(changeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener changeListener) {
        support.removePropertyChangeListener(changeListener);
    }

    public void setNews(String value) {
        support.firePropertyChange("news", this.news, value);
        this.news = value;

    }
}