/**
 * Created by woowabrothers on 2017. 8. 4..
 */
public class Restaurants {

    private String restaurantsName;
    private String restaurantsAddress;
    private String restaurantsTel;
    private String restaurantsImgUrl;
    private String restaurantsDescription;
    private String restaurantsRunningTime;

    public Restaurants(String restaurantsName,
                       String restaurantsAddress,
                       String restaurantsTel,
                       String restaurantsImgUrl,
                       String restaurantsDescription,
                       String restaurantsRunningTime) {
        this.restaurantsName = restaurantsName;
        this.restaurantsAddress = restaurantsAddress;
        this.restaurantsTel = restaurantsTel;
        this.restaurantsImgUrl = restaurantsImgUrl;
        this.restaurantsDescription = restaurantsDescription;
        this.restaurantsRunningTime = restaurantsRunningTime;
    }

    public String getRestaurantsName() { return restaurantsName; }
    public String getRestaurantsAddress() { return restaurantsAddress; }
    public String getRestaurantsTel() { return restaurantsTel; }
    public String getRestaurantsImgUrl() { return restaurantsImgUrl; }
    public String getRestaurantsDescription() { return restaurantsDescription; }
    public String getRestaurantsRunningTime() { return restaurantsRunningTime; }

    public void setRestaurantsName(String restaurantsName) { this.restaurantsName = restaurantsName; }
    public void setRestaurantsAddress(String restaurantsAddress) {this.restaurantsAddress = restaurantsAddress; }
    public void setRestaurantsTel(String restaurantsTel) { this.restaurantsTel = restaurantsTel; }
    public void setRestaurantsImgUrl(String restaurantsImgUrl) { this.restaurantsImgUrl = restaurantsImgUrl; }
    public void setRestaurantsDescription(String restaurantsDescription) { this.restaurantsDescription = restaurantsDescription; }
    public void setRestaurantsRunningTime(String restaurantsRunningTime) { this.restaurantsRunningTime = restaurantsRunningTime; }
}
