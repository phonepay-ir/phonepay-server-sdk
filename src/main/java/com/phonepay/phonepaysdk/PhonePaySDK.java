package com.phonepay.phonepaysdk;

import com.phonepay.phonepaysdk.domain.APIToken;
import com.phonepay.phonepaysdk.domain.OTP;
import com.phonepay.phonepaysdk.domain.OTPRequest;
import com.phonepay.phonepaysdk.network.HttpClient;

public class PhonePaySDK {
    private String apiKey;
    private String phoneNumber;

    public PhonePaySDK apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public PhonePaySDK phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OTP requestOTP() {
        return (OTP) new HttpClient().sendRequest(apiKey,
                OTPRequest.Builder.with()
                        .phoneNumber(phoneNumber)
                        .build(), OTP.class
        );
    }

    public APIToken requestApiToken() {
        return (APIToken) new HttpClient().sendRequest(apiKey, null, APIToken.class);
    }
}
