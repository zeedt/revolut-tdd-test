package com.revolut;

public class LoadBalancerService {
    final LoadBalancerRepository loadBalancerRepository;
    public LoadBalancerService(LoadBalancerRepository loadBalancerRepository) {
        this.loadBalancerRepository = loadBalancerRepository;
    }

    public boolean registerIpAddress(String ipAddress) throws LoadBalancerOutOfBoundException {
        return loadBalancerRepository.registerIpAddress(ipAddress);
    }

    public String getRandomIp() {
        return loadBalancerRepository.getIpAddressRandomly();
    }
}
