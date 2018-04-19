package com.phonepay.phonepaysdk.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepay.phonepaysdk.exception.InvalidRequestException;
import com.phonepay.phonepaysdk.exception.InvalidResponseException;
import com.phonepay.phonepaysdk.exception.NetworkException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    private static final String SERVER_URL = "https://darban.phonepay.ir/api/v1.0";
    private static final String OTP_URL = SERVER_URL + "/darban/otp";

    private static final String USER_AGENT = "PhonePaySDK/v1.0";

    public Object sendRequest(String apiKey, Request request, Class responseClass) {
        try {
            URL url = new URL(OTP_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("api-key", apiKey);

            String response = null;

            if (request != null)
                response = sendPost(urlConnection, request.data());


            if (response != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response, responseClass);
            }

            return null;
        } catch (IOException e) {
            throw new NetworkException();

        }
    }

    private String sendPost(HttpURLConnection httpURLConnection, String data) {
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
            httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            httpURLConnection.setDoOutput(true);

            try {
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();

            } catch (IOException e) {
                throw new InvalidRequestException();

            }


            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK)
                throw new InvalidResponseException();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            return response.toString();

        } catch (IOException e) {
            throw new NetworkException();

        }
    }
}
