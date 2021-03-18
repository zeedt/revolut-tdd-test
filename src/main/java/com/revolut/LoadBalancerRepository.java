package com.revolut;

public interface LoadBalancerRepository {
    boolean registerIpAddress(String ipAddress) throws LoadBalancerOutOfBoundException;

    String getIpAddressRandomly();
}
