package tourguide.lightidea.com.tourguide.model.RestaurantModel;

public class TraditionalFoodModel {
    public TraditionalFoodModel(String name, String url, String data, String park, String book, String phone, String address) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.park = park;
        this.book = book;
        this.phone = phone;
        this.address = address;
    }

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

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String name,url,data;
    private String park,book;
    private String phone,address;


    public TraditionalFoodModel(){

    }
}
