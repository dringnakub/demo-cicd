package com.happy.shoppingcart.api.service;

import com.happy.shoppingcart.api.service.domain.VisaDetail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class VisaService {

    @Autowired
    private RestTemplate restTemplate;

    public String verifyVisa(VisaDetail request, int total) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        int total_amt=total;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("card_number", request.getCardNumber());
        jsonObject.put("cvv",request.getCvv());
        jsonObject.put("exe", request.getExpireMonth()+"/"+request.getExpireYear());
        jsonObject.put("total_amt", total_amt);

        String url = "http://localhost:4000/visa/api/verify";
        HttpEntity<String> visaRequest =
                new HttpEntity<String>(jsonObject.toString(),headers);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST,visaRequest, String.class);

        System.out.println("body of json : "+result.getBody());

        JSONObject resultObject = new JSONObject(result.getBody());
        System.out.println((String)resultObject.get("status_message"));

        String visaApprove = (String)resultObject.get("status_message");

        return visaApprove;
    }
}
