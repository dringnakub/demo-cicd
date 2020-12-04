package com.happy.shoppingcart.api.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class TrackingService {
    @Autowired
    private RestTemplate restTemplate;
    private String trackingNumber;
    public String createTrackingNumber(String address1, String address2, String postCode, String country, String mobileNumber) throws JSONException {
        String url = "http://localhost:4000/kerry/api/create_tracking";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addr1", address1);
        jsonObject.put("addr2", address2);
        jsonObject.put("post_code", postCode);
        jsonObject.put("country", country);
        jsonObject.put("mobile_number", mobileNumber);
        HttpEntity<String> trackingRequest =
                new HttpEntity<String>(jsonObject.toString(),headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST,trackingRequest, String.class);
        System.out.println("body of json : "+result.getBody());
        JSONObject resultObject = new JSONObject(result.getBody());
        System.out.println((String)resultObject.get("tracking_num"));
        trackingNumber = (String)resultObject.get("tracking_num");
        return trackingNumber;
    }

}
