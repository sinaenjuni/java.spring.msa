package com.msa.kakaoapi.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class LocationAPI {

    private String strUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private String api_key = "KakaoAK 368a388a853d90a7575a99ce6d669b64";
    private RestTemplate restTemplate = new RestTemplate();

    ParameterizedTypeReference<Map<String, Object>> typeRef =
            new ParameterizedTypeReference<Map<String, Object>>() {};


    public JSONArray getLocation(String keyword, int page, int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", api_key);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(strUrl)
//                .queryParam("query", "도안") 한글 인코딩 문제가 있음
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("category_group_code", "FD6");

        String uri = builder.encode().toUriString() + "&query=" + keyword;

        ResponseEntity responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                String.class);


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
//        JSONArray docuArray = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());
//            System.out.println(docuArray);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get("documents");

//        JSONObject docuObject = (JSONObject) docuArray.get(1);
//        System.out.println(docuObject.get("place_url"));

        return jsonArray;
    }
}
