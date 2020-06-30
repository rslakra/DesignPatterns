package com.devamatre.designpatterns.behavioral.chainofresponsibility;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 7:55 PM
 * Version: 1.0.0
 */
public class ChainOfResponsibilityIntegrationTest {

    public static void main(String[] args) {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();

        // OAuthTokenProvider
        boolean isAuthorized = authProcessorChain.isAuthorized(new OAuthTokenProvider());
        System.out.println("isAuthorized:" + isAuthorized);

        // UsernamePasswordProvider
        isAuthorized = authProcessorChain.isAuthorized(new UsernamePasswordProvider());
        System.out.println("isAuthorized:" + isAuthorized);


        // SamlAuthenticationProvider
        isAuthorized = authProcessorChain.isAuthorized(new SamlAuthenticationProvider());
        System.out.println("isAuthorized:" + isAuthorized);
    }

    /**
     * @return
     */
    private static AuthenticationProcessor getChainOfAuthProcessor() {
        AuthenticationProcessor oAuthProcessor = new OAuthAuthenticationProcessor(null);
        AuthenticationProcessor unamePasswordProcessor = new UsernamePasswordAuthenticationProcessor(oAuthProcessor);
        return unamePasswordProcessor;
    }

}
