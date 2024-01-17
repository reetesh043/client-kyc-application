package com.example.kyc.controller;

import com.example.kyc.model.KycRequest;
import com.example.kyc.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KYCController {

    private final KYCService kycService;

    @PostMapping("/kyc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void performKyc(@RequestBody KycRequest kycRequest) {
        kycService.performKycAndNotifyClient(kycRequest);
    }
}
