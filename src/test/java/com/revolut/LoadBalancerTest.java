package com.revolut;

import org.junit.jupiter.api.*;

/**
 * Register instances to the Load Balancer – the max number of accepted backend-instances from the
 * load balancer is 10. Each instance should have a unique address.
 */
public class LoadBalancerTest {

    private LoadBalancerService loadBalancerService;
    @BeforeEach
    public void init() {
         loadBalancerService = new LoadBalancerService(new LoadBalancerRepositoryStub());
    }

    @Test
    @DisplayName(("Should be able to register an IP Address"))
    public void testIpRegistration() throws LoadBalancerOutOfBoundException {
        boolean response = loadBalancerService.registerIpAddress("127.0.0.1");
        Assertions.assertTrue(response, "Should get positive response for IP registration");
    }

    @Test
    @DisplayName("Confirm it should contain unique IP Address")
    public void testUniqueIpAddress() throws LoadBalancerOutOfBoundException {
        String ipAddress = "127.0.0.1";
        boolean response = loadBalancerService.registerIpAddress(ipAddress);
        Assertions.assertTrue(response);
        response = loadBalancerService.registerIpAddress(ipAddress);
        Assertions.assertFalse(response, "Load balancer should contain unique IP Address");
    }

    @Test
    @DisplayName("Should not contain more than 10 IP Addresses")
    public void testNotMoreThan10IPAddresses() throws LoadBalancerOutOfBoundException {
        for (int i=0;i<10;i++) {
            loadBalancerService.registerIpAddress("127.0.0."+i);
        }
        Assertions.assertThrows(LoadBalancerOutOfBoundException.class, () -> loadBalancerService.registerIpAddress("212.232.56.18"));
    }

    @Test
    @DisplayName("Should return at an IP address at Random")
    public void testAnIpAddresIsReturnedAtRandom() throws LoadBalancerOutOfBoundException {
        for (int i=0;i<10;i++) {
            loadBalancerService.registerIpAddress("127.0.0."+i);
        }
        String ipAddress = loadBalancerService.getRandomIp();
        Assertions.assertNotNull(ipAddress, "Returned ipAddress must not be null");
    }

    @Test
    @DisplayName(("Should return diffferent IP Addresss to confirm the randomization"))
    public void testRandomWorks() throws LoadBalancerOutOfBoundException {
        for (int i=0;i<10;i++) {
            loadBalancerService.registerIpAddress("127.0.0."+i);
        }
        String ipAddress = loadBalancerService.getRandomIp();
        Assertions.assertTrue(!ipAddress.equals(loadBalancerService.getRandomIp()), "IP addresses expected to be different");
    }

}
