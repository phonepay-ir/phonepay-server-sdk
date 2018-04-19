package com.phonepay.phonepaysdk.domain;

import java.sql.Timestamp;

public class OTP {
    private String otp;
    private Timestamp expireInSecond;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getExpireInSecond() {
        return expireInSecond;
    }

    public void setExpireInSecond(String Timestamp) {
        this.expireInSecond = expireInSecond;
    }
}
