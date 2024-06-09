package com.example.ping_service.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;

@Service
public class IpService {

    private static final String IPIFY_API_URL = "https://api.ipify.org?format=json";
    private static final String IPINFO_API_URL = "https://ipinfo.io/";
    private static final Logger logger = LogManager.getLogger(IpService.class);

    public String getPublicIp() {
        logger.info("Fetching public IP address.");
        RestTemplate restTemplate = new RestTemplate();
        IpResponse response = restTemplate.getForObject(IPIFY_API_URL, IpResponse.class);
        String ip = response.getIp();
        logger.info("Public IP address fetched: {}", ip);
        return ip;
    }

    public IpInfo getIpInfo(String ipAddress) {
        logger.info("Fetching IP information for IP address: {}", ipAddress);
        RestTemplate restTemplate = new RestTemplate();
        IpInfo ipInfo = restTemplate.getForObject(IPINFO_API_URL + ipAddress, IpInfo.class);
        logger.info("IP information fetched: {}", ipInfo);
        return ipInfo;
    }

    public double getDownloadSpeed() {
        logger.info("Simulating download speed.");
        // Simulate download speed (Mbps)
        double downloadSpeed = 50.0 + Math.random() * 50.0;
        logger.info("Download speed: {} Mbps", downloadSpeed);
        return downloadSpeed;
    }

    public double getUploadSpeed() {
        logger.info("Simulating upload speed.");
        // Simulate upload speed (Mbps)
        double uploadSpeed = 10.0 + Math.random() * 20.0;
        logger.info("Upload speed: {} Mbps", uploadSpeed);
        return uploadSpeed;
    }

    @Getter
    @Setter
    private static class IpResponse {
        private String ip;
    }

    @Getter
    @Setter
    public static class IpInfo {
        private String ip;
        private String hostname;
        private String city;
        private String region;
        private String country;
        private String loc;
        private String org;
        private String postal;
        private String timezone;
    }
}
