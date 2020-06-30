package com.devamatre.designpatterns.behavioral.observer;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:57 PM
 * Version: 1.0.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        ObserverTest observerTest = new ObserverTest();
        observerTest.whenChangingNewsAgencyStateThenNewsChannelNotified();
        observerTest.whenChangingNewsAgencyStateThenNewsChannelObserverNotified();
        observerTest.whenChangingPCLNewsAgencyStateThenPCLNewsChannelNotified();
    }

    public void whenChangingNewsAgencyStateThenNewsChannelNotified() {
        NewsAgency observable = new NewsAgency();
        NewsChannel observer = new NewsChannel();

        observable.addObserver(observer);
        observable.setNews("news");
        System.out.println("news".equals(observer.getNews()));
    }

    /**
     *
     */
    public void whenChangingNewsAgencyStateThenNewsChannelObserverNotified() {
        NewsAgencyObservable observable = new NewsAgencyObservable();
        NewsChannelObserver observer = new NewsChannelObserver();
        observable.addObserver(observer);
        observable.setNews("news");
        System.out.println("news".equals(observer.getNews()));
    }

    /**
     *
     */
    public void whenChangingPCLNewsAgencyStateThenPCLNewsChannelNotified() {
        PCLNewsAgency observable = new PCLNewsAgency();
        PCLNewsChannel observer = new PCLNewsChannel();

        observable.addPropertyChangeListener(observer);
        observable.setNews("news");
        System.out.println("news".equals(observer.getNews()));
    }
}
