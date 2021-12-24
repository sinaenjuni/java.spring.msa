package com.msa.kakaoapi;

import com.msa.kakaoapi.api.LocationAPI;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KakaoAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoAPIApplication.class, args);

    }

}
