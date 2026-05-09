package com.rslakra.designpatterns.behavioral.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NewsAgencyTest {

    @Test
    void setNews_notifiesRegisteredChannel() {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel();
        agency.addObserver(channel);

        agency.setNews("headline");

        assertEquals("headline", channel.getNews());
    }

    @Test
    void removeObserver_stopsNotifications() {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel();
        agency.addObserver(channel);
        agency.removeObserver(channel);

        agency.setNews("ignored");

        assertNull(channel.getNews());
    }
}
