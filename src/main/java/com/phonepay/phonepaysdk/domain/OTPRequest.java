package com.phonepay.phonepaysdk.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepay.phonepaysdk.exception.InvaldiRequestDataException;
import com.phonepay.phonepaysdk.network.Request;

public class OTPRequest implements Request {
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public static final class Builder {
        private String phoneNumber;

        private Builder() {
        }

        public static Builder with() {
            return new Builder();
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OTPRequest build() {
            OTPRequest oTPRequest = new OTPRequest();
            oTPRequest.setPhoneNumber(phoneNumber);
            return oTPRequest;
        }
    }

    @Override
    public String data() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new InvaldiRequestDataException();

        }
    }
}
