package app.nirmlkar.dalejan.bluedart.generic;


public class ItemDetails {

    private String details_id;
    private String boy_id;
    private String Item_name;
    private String pickup_place;
    private String drop_place;
    private String flag;

    private double latitude;
    private double longitude;

    public String getDetails_id() {
        return details_id;
    }

    public void setDetails_id(String details_id) {
        this.details_id = details_id;
    }

    public String getBoy_id() {
        return boy_id;
    }

    public void setBoy_id(String boy_id) {
        this.boy_id = boy_id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getPickup_place() {
        return pickup_place;
    }

    public void setPickup_place(String pickup_place) {
        this.pickup_place = pickup_place;
    }

    public String getDrop_place() {
        return drop_place;
    }

    public void setDrop_place(String drop_place) {
        this.drop_place = drop_place;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
