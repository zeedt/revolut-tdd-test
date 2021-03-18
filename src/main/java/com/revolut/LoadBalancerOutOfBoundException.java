package com.revolut;

public class LoadBalancerOutOfBoundException extends Throwable {
    public LoadBalancerOutOfBoundException(String message) {
        super(message);
    }
}
