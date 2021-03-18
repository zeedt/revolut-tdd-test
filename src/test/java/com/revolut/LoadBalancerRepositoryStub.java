package com.revolut;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancerRepositoryStub implements LoadBalancerRepository {

    final List<String> ipAddresses = new ArrayList<>();

    public boolean registerIpAddress(String ipAddress) throws LoadBalancerOutOfBoundException {
        if (ipAddresses.size()>=10)
            throw new LoadBalancerOutOfBoundException("Load balancer can only contain maximum of 10 IP Addresses");
        if (ipAddresses.contains(ipAddress))
            return false;
        return ipAddresses.add(ipAddress);
    }

    @Override
    public String getIpAddressRandomly() {
        Random random = new Random();
        return ipAddresses.get(random.nextInt(ipAddresses.size()));
    }
}
