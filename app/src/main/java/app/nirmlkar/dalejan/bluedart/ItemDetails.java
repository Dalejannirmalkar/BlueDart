package app.nirmlkar.dalejan.bluedart;


class ItemDetails {

    private String details_id;
    private String boy_id;
    private String Item_name;
    private String pickup_place;
    private String drop_place;
    private String flag;

    private double latitude;
    private double longitude;

    double getLatitude() {
        return latitude;
    }

     void setLatitude(double latitude) {
        this.latitude = latitude;
    }

     double getLongitude() {
        return longitude;
    }

     void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    String getDetails_id() {
        return details_id;
    }

     void setDetails_id(String details_id) {
        this.details_id = details_id;
    }

    String getBoy_id() {
        return boy_id;
    }

    void setBoy_id(String boy_id) {
        this.boy_id = boy_id;
    }

    String getItem_name() {
        return Item_name;
    }

    void setItem_name(String item_name) {
        Item_name = item_name;
    }

    String getPickup_place() {
        return pickup_place;
    }

    void setPickup_place(String pickup_place) {
        this.pickup_place = pickup_place;
    }

    String getDrop_place() {
        return drop_place;
    }

    void setDrop_place(String drop_place) {
        this.drop_place = drop_place;
    }

    String getFlag() {
        return flag;
    }

    void setFlag(String flag) {
        this.flag = flag;
    }
}
