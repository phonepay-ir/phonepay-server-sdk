package com.phonepay.phonepaysdk.network;

import com.phonepay.phonepaysdk.exception.InvaldiRequestDataException;

public interface Request {
    String data() throws InvaldiRequestDataException;
}
