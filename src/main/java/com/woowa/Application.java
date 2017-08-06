package com.woowa;

import com.woowa.controller.Controller;
import com.woowa.crawler.DiningcodeCrawler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woowabrothers on 2017. 8. 2..
 */
public class Application {
    public static void main(String args[]) {
        Controller controller= new Controller("송파구", new DiningcodeCrawler());
        controller.crawling();
    }
}


//restaurantInfo[0] = restaurantName;
//        restaurantInfo[1] = restaurantAddress;
//        restaurantInfo[2] = restaurantTel;
//        restaurantInfo[3] = restaurantPictureUrl;
//        restaurantInfo[4] = restaurantDescription;
//        restaurantInfo[5] = restaurantRunningWeekDay + restaurantRunningTime;