package com.example.kyc.service;

import com.example.kyc.model.KycRequest;
import com.example.kyc.model.WebhookNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KYCService {

    private static final String KYC_COMPLETED_STATUS = "KYC_COMPLETED";
    RestTemplate restTemplate = new RestTemplate();

    @Value("${clientService.webhook.url}")
    private String clientServiceUrl;


    public void performKycAndNotifyClient(final KycRequest kycRequest) {
        log.info("Kyc data received: {}", kycRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        // Write Logic To Perform KYC
        // Once KYC is done, notify client kyc status via webhook url that KYC is completed.
        try {
            HttpEntity<WebhookNotification> request = new HttpEntity<>(WebhookNotification.builder().clientId(kycRequest.getClientId()).kycStatus(KYC_COMPLETED_STATUS).build(), headers);
            restTemplate.exchange(clientServiceUrl, HttpMethod.POST, request, Void.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("Client notified successfully.");

    }
}
