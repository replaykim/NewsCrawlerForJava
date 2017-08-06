import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by woowabrothers on 2017. 8. 3..
 */
public class DiningcodeCrawler {

    public ArrayList<String[]> searchRestaurantByLocal(String localName) throws IOException {

        ArrayList<String[]> restaurantInfoList = new ArrayList<String[]>();
        //컬럼설정
//        String[] restaurantInfo = new String[6];
//        restaurantInfo[0] = "식당이름";
//        restaurantInfo[1] = "식당주소";
//        restaurantInfo[2] = "식당전화번호";
//        restaurantInfo[3] = "식당이미지";
//        restaurantInfo[4] = "식당설명";
//        restaurantInfo[5] = "식당영업정보";
//        restaurantInfoList.add(restaurantInfo);

        ArrayList<String> restaurantList = new ArrayList<String>();

        int page = 1;
        Document doc;
        Elements restaurantDetailUrlList;

        while (true) {
            System.out.println(""+page+"번째 크롤링입니다.");

            String url = " http://www.diningcode.com/list.php?page=" + page + "&chunk=10&query=" + localName + "";
            doc = Jsoup.connect(url).get();
            restaurantDetailUrlList = doc.select(".dc-restaurant-name");
            if (page == 10 || restaurantDetailUrlList == null) break;
            //System.out.println(restaurantDetailUrlList);

            int arrayIndex = 0;
            while (arrayIndex < restaurantDetailUrlList.size()) {
                Elements attry = restaurantDetailUrlList.get(arrayIndex).select("a");
                restaurantList.add(attry.attr("href"));
                arrayIndex++;
            }

            for (int i = 0; i < restaurantList.size(); i++) {
                restaurantInfoList.add(detailParser(restaurantList.get(i)));
            }
            page++;
        }


        return restaurantInfoList;
    }

    private String[] detailParser(String detailHash) throws IOException {
        String basicUrl = "http://www.diningcode.com/";
        String url = basicUrl + detailHash;
        String[] restaurantInfo = new String[6];

        Document doc = Jsoup.connect(url).get();

        //식당 이름 쿼리
        Elements restaurantNameElement = doc.select(".item-rn-title");
        String restaurantName = restaurantNameElement.text();
        System.out.println(restaurantName);

        //식당 주소 쿼리
        Element restaurantAddressElement;
        if (doc.select(".item-information-text").size() == 4) {
            restaurantAddressElement = doc.select(".item-information-text").get(2);
        } else {
            restaurantAddressElement = doc.select(".item-information-text").get(1);
        }
        String restaurantAddress = restaurantAddressElement.text();
        System.out.println(restaurantAddress);

        //식당 전화번호
        //식당 전화번호가 옵션값이 있을떄와 없을때 차이
        Element restaurantTelElement;
        if (doc.select(".item-information-text").size() == 4) {
            restaurantTelElement = doc.select(".item-information-text").get(3);
        } else {
            restaurantTelElement = doc.select(".item-information-text").get(2);
        }
        String restaurantTel = restaurantTelElement.text();
        System.out.println(restaurantTel);

        //식당 사진
        Elements restaurantPictureElement = doc.select("img[id=p0]");
        String restaurantPictureUrl = restaurantPictureElement.attr("src");
        System.out.println(restaurantPictureUrl);

        //식당 한줄 소개
        Element restaurantDescriptionElement = doc.select(".item-information-text").first();
        String restaurantDescriptionRaw = restaurantDescriptionElement.text();
        String restaurantDescription = restaurantDescriptionRaw.replaceAll(",", "/");
        System.out.println(restaurantDescription);

        //식당 영업시간
        Element restaurantRunningTimeElement;
        String restaurantRunningWeekDayRaw;
        String restaurantRunningTime;
        Elements restaurantRunningWeekDayElement = doc.select(".rest-time-left");

        //영업시간이 없는 식당 예외
        if (doc.select(".time").size() != 0) {
            restaurantRunningTimeElement = doc.select(".time").get(0);
            restaurantRunningWeekDayRaw = restaurantRunningWeekDayElement.text();
            restaurantRunningTime = restaurantRunningTimeElement.text();
        } else {
            restaurantRunningWeekDayRaw = "";
            restaurantRunningTime = "";
        }
        String restaurantRunningWeekDay = restaurantRunningWeekDayRaw.replaceAll(",", "/");
        System.out.println(restaurantRunningWeekDay);
        System.out.println(restaurantRunningTime);


        restaurantInfo[0] = restaurantName;
        restaurantInfo[1] = restaurantAddress;
        restaurantInfo[2] = restaurantTel;
        restaurantInfo[3] = restaurantPictureUrl;
        restaurantInfo[4] = restaurantDescription;
        restaurantInfo[5] = restaurantRunningWeekDay + restaurantRunningTime;

        return restaurantInfo;
    }
}
