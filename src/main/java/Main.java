import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woowabrothers on 2017. 8. 2..
 */
public class Main {

    public static void main(String args[]) {

        DiningcodeCrawler diningcodeCrawler = new DiningcodeCrawler();
        CSVMaker csvMaker = new CSVMaker("송파구.csv");
        ArrayList<String[]> restaurantInfoList = new ArrayList<String[]>();

        JSONObject jsonObject = new JSONObject();

        try {
            JSONArray jsonArray = new JSONArray();
            restaurantInfoList = diningcodeCrawler.searchRestaurantByLocal("송파구");
            for (int i=0; i<restaurantInfoList.size(); i++) {
                System.out.println(restaurantInfoList.get(i)[0]);
                System.out.println(restaurantInfoList.get(i)[1]);
                System.out.println(restaurantInfoList.get(i)[2]);
                System.out.println(restaurantInfoList.get(i)[3]);
                System.out.println(restaurantInfoList.get(i)[4]);
                System.out.println("=============================");

                JSONObject object = new JSONObject();
                object.put("restaurant_name", restaurantInfoList.get(i)[0]);
                object.put("restaurant_address", restaurantInfoList.get(i)[1]);
                object.put("restaurant_tel", restaurantInfoList.get(i)[2]);
                object.put("restaurant_pictrue_url", restaurantInfoList.get(i)[3]);
                object.put("restaurant_description", restaurantInfoList.get(i)[4]);
                object.put("restaurant_running_time", restaurantInfoList.get(i)[5]);
                jsonArray.put(object);
            }
            jsonObject.put("item", jsonArray);

            FileWriter file = new FileWriter("송파구.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();

            csvMaker.makeCSV(restaurantInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//restaurantInfo[0] = restaurantName;
//        restaurantInfo[1] = restaurantAddress;
//        restaurantInfo[2] = restaurantTel;
//        restaurantInfo[3] = restaurantPictureUrl;
//        restaurantInfo[4] = restaurantDescription;
//        restaurantInfo[5] = restaurantRunningWeekDay + restaurantRunningTime;