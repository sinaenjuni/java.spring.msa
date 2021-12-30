package com.msa.kakaoapi;


import com.msa.kakaoapi.api.AttachImList;
import com.msa.kakaoapi.api.LocationAPI;
import com.msa.kakaoapi.dto.Location;

import com.sun.media.jfxmedia.logging.Logger;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.flogger.Flogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("kakaoapi")
@RequestMapping
public class KakaoAPIController {

    @Autowired
    private LocationAPI locationAPI;

    @Autowired
    private AttachImList attachImList;

    @GetMapping("locations/{keyword}/{page}/{size}")
    public JSONArray getLocation(@PathVariable String keyword, @PathVariable int page, @PathVariable int size){
        JSONArray result = locationAPI.getLocation(keyword, page, size);

        return result;
    }

    @GetMapping("images/{keyword}/{page}/{size}/{img_count}")
    public JSONArray getImages(@PathVariable String keyword, @PathVariable int page, @PathVariable int size, @PathVariable int img_count)  {
        JSONArray results = attachImList.getAttachImList(keyword, page, size, img_count);

        return results;
    }


}
