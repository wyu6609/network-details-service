package com.example.ping_service.controller;

import com.example.ping_service.service.IpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PingController {

    private static final Logger logger = LogManager.getLogger(PingController.class);

    private final IpService ipService;

    @Autowired
    public PingController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping("/ping")
    public Map<String, Object> ping() {

        String localTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.info("Received request for /ping endpoint. Local time: {}", localTime);

        Map<String, Object> response = new HashMap<>();
        String publicIp = ipService.getPublicIp();
        double downloadSpeed = ipService.getDownloadSpeed();
        double uploadSpeed = ipService.getUploadSpeed();
        IpService.IpInfo ipInfo = ipService.getIpInfo(publicIp);

        response.put("publicIp", publicIp);
        response.put("localTime", localTime);
        response.put("downloadSpeed", downloadSpeed);
        response.put("uploadSpeed", uploadSpeed);

        response.put("isp", ipInfo.getOrg());
        response.put("city", ipInfo.getCity());
        response.put("region", ipInfo.getRegion());
        response.put("country", ipInfo.getCountry());

        logger.info("Response: {}", response);
        return response;
    }
}
