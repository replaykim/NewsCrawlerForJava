package com.woowa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woowabrothers on 2017. 8. 2..
 */


public class WebCrawlerByJsoup {

    public ArrayList<String[]> searchRestaurantByLocal(String localName) throws IOException {
        ArrayList<String[]> restaurantInfoList = new ArrayList<String[]>();

        int page = 1;
        String url = "https://www.mangoplate.com/search/"+localName+"?keyword="+localName+"&page="+page+"";
        ArrayList<String> restaurantList = new ArrayList<String>();

        Document doc = Jsoup.connect(url).get();
        Elements restaurantDetailUrlList = doc.select(".info a");

        int arrayIndex = 0;
        while (arrayIndex < restaurantDetailUrlList.size()) {
            restaurantList.add(restaurantDetailUrlList.get(arrayIndex).attr("href"));
            arrayIndex++;
        }

        for(int i=0; i<restaurantList.size(); i++) {
            restaurantInfoList.add(detailParser(restaurantList.get(i)));
        }

        return restaurantInfoList;
    }

    private String[] detailParser(String detailHash) throws IOException {
        String basicUrl = "https://www.mangoplate.com/restaurants";
        String url = basicUrl + detailHash;
        String[] restaurantInfo = new String[3];

        Document doc = Jsoup.connect(url).get();

        //식당 이름 쿼리
        Elements restaurantNameElement = doc.select("h1");
        String restaurantName = restaurantNameElement.text();

        //식당 한줄 소개
//        Elements restaurantDescriptionElement = doc.select(".desc p");
//        String restaurantDescription = restaurantDescriptionElement.text();

        //식당 주소 쿼리
        Element restaurantAddressElement = doc.select("span[class=orange-underline]").first();
        String restaurantAddress = restaurantAddressElement.text();

        //식장 전화번호
        Element restaurantTelElement = doc.select("span[class=orange-underline]").get(1);
        String restaurantTel = restaurantTelElement.text();


//        System.out.println(restaurantName);
//        System.out.println(restaurantAddress);
//        System.out.println(restaurantTel);

        restaurantInfo[0] = restaurantName;
        restaurantInfo[1] = restaurantAddress;
        restaurantInfo[2] = restaurantTel;

        return restaurantInfo;
    }
}
