package tourguide.lightidea.com.tourguide.model.HotelModel;

public class HotelModel {
    public HotelModel(String name,String rating,String url, String data, String phone, String location, String price, String room, String reroom) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.phone = phone;
        this.location = location;
        this.price = price;
        this.room = room;
        this.reroom = reroom;
        this.rating =rating;
    }

    private  String name,url,data;
    private String phone,location,price,room,reroom;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getReroom() {
        return reroom;
    }

    public void setReroom(String reroom) {
        this.reroom = reroom;
    }

    public HotelModel(){

    }
}
