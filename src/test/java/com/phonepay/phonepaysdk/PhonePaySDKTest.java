package com.phonepay.phonepaysdk;


import com.phonepay.phonepaysdk.domain.OTP;
import org.junit.Assert;
import org.junit.Test;

public class PhonePaySDKTest {
    @Test
    public void checkSDK() {
        OTP otp = new PhonePaySDK()
                .apiKey("13335761-d20b-4d7b-8703-91a962e3c2bd")
                .phoneNumber("+989129057266")
                .requestOTP();

        Assert.assertNotNull(otp);
    }
}
