package tourguide.lightidea.com.tourguide.model.RestaurantModel;

public class TraditionalFoodModel {
    private String name,url,data;
    private String name_bur,name_chi;
    private String park,book;
    private String phone,address;
    private String phone_bur,phone_chi;
    private String address_bur,address_chi;

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

    public String getName_bur() {
        return name_bur;
    }

    public void setName_bur(String name_bur) {
        this.name_bur = name_bur;
    }

    public String getName_chi() {
        return name_chi;
    }

    public void setName_chi(String name_chi) {
        this.name_chi = name_chi;
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

    public String getPhone_bur() {
        return phone_bur;
    }

    public void setPhone_bur(String phone_bur) {
        this.phone_bur = phone_bur;
    }

    public String getPhone_chi() {
        return phone_chi;
    }

    public void setPhone_chi(String phone_chi) {
        this.phone_chi = phone_chi;
    }

    public String getAddress_bur() {
        return address_bur;
    }

    public void setAddress_bur(String address_bur) {
        this.address_bur = address_bur;
    }

    public String getAddress_chi() {
        return address_chi;
    }

    public void setAddress_chi(String address_chi) {
        this.address_chi = address_chi;
    }

    public TraditionalFoodModel(String name, String url, String data, String name_bur, String name_chi, String park, String book, String phone, String address, String phone_bur, String phone_chi, String address_bur, String address_chi) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.name_bur = name_bur;
        this.name_chi = name_chi;
        this.park = park;
        this.book = book;
        this.phone = phone;
        this.address = address;
        this.phone_bur = phone_bur;
        this.phone_chi = phone_chi;
        this.address_bur = address_bur;
        this.address_chi = address_chi;
    }

    public TraditionalFoodModel(){

    }
}
