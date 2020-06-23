package com.devamatre.designpatterns.structural.proxy;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:28 PM
 * Version: 1.0.0
 */
public class ProxyInternet implements Internet {

    private Internet internet = new RealInternet();
    private static List<String> blockedSites = Arrays.asList("abc.com", "def.com", "ijk.com", "lmn.com", "pqr.com");

    /**
     * @param host
     */
    @Override
    public void connect(String host) {
        if (blockedSites.contains(host.toLowerCase())) {
            throw new RuntimeException("Access Denied");
        }

        internet.connect(host);
    }
}
