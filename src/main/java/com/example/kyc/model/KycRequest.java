package com.example.kyc.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class KycRequest {

    private BigInteger clientId;

    private String name;

    private String dob;

    private String taxId;

    private String address;

    private String email;
}
