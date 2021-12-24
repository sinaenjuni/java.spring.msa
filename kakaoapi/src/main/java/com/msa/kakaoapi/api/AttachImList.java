package com.msa.kakaoapi.api;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.Doc;
import java.io.IOException;

@Service
public class AttachImList {

    public String getFirstPlaceURL(String place_id){
        return "https://place.map.kakao.com/photolist/v/" + place_id + "?logevent=photo%2Cphoto_view&logtarget=&pidx=0";
    }
    public String getMorePlaceURL(String place_id, String more_place_id){
        return "https://place.map.kakao.com/photolist/v/" + place_id + "?moreid=" + more_place_id + "&type=all&basis=all";
    }

    public JSONObject getFirstPlaceImg(String url){
        JSONParser jsonParser = new JSONParser();
        JSONObject resultJsonObject = null;

        try {
            resultJsonObject = (JSONObject) jsonParser.parse(
                    Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.76 Whale/3.12.129.34 Safari/537.36")
                    .get().body().text());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultJsonObject;
    }

    public JSONObject getMorePlaceImg(String url){
        JSONParser jsonParser = new JSONParser();
        JSONObject resultJsonObject = null;
        try {
            resultJsonObject = (JSONObject) jsonParser.parse(
                    Jsoup.connect(url)
                            .ignoreContentType(true)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.76 Whale/3.12.129.34 Safari/537.36")
                            .get().body().text());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultJsonObject;
    }

    @Autowired
    private LocationAPI locationAPI;
    private RestTemplate restTemplate = new RestTemplate();

    private String more_place_url = "";

    public JSONArray getAttachImList(String keyword, int page, int size, int img_count) {

        JSONArray jsonArray = locationAPI.getLocation(keyword, page, size);
        JSONArray results = new JSONArray();

        int iter_more = img_count/30;

        for(Object object: jsonArray){
            JSONObject placeObject = (JSONObject) object;
            String place_id = placeObject.get("id").toString();

            JSONObject place_img_result = getFirstPlaceImg(getFirstPlaceURL(place_id));
            JSONObject viewObject = (JSONObject) place_img_result.get("photoViewer");
            int photoCnt = Integer.parseInt(viewObject.get("photoCnt").toString());

            System.out.println(viewObject);
            JSONArray place_img_list = (JSONArray) viewObject.get("list");
            System.out.println(place_img_list);


            System.out.println(photoCnt);

            if(photoCnt == 0){
                continue;
            } else if(photoCnt < 30){

            }else{
                String moreId = viewObject.get("moreId").toString();
            }

            placeObject.put("img_list", place_img_list);

//            jsonObject.put()
            results.add(placeObject);


//            for(int i=0; i<iter_more; i++){
//                place_result = getMorePlaceImg(getMorePlaceURL(place_id, moreId));
//                viewObject = (JSONObject) place_result.get("photoViewer");
//                moreId = viewObject.get("moreId").toString();
//
//                JSONArray list = (JSONArray) viewObject.get("list");
//                results.add(place_result);
//
//            }

//
//
//            ((JSONObject) object).putAll(resultJsonObject);

//
//
//            System.out.println(viewObject);
//            System.out.println(moreId);
//            System.out.println(photoCnt);
//            System.out.println(list);
//            System.out.println(list.size());

//            results.add(object);

//            results.add

//            Elements elements = doc.select("#photoViewer > div.layer_body > div.item_photo > div > ul > li:nth-child(1)");
//            System.out.println(place_url);
//            System.out.println(resultJsonObject);
//            System.out.println("awdaw" + elements);

        }

        return results;
    }
}
