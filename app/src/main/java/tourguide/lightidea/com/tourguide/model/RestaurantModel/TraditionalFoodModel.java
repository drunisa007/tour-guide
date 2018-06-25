package tourguide.lightidea.com.tourguide.model.RestaurantModel;

public class TraditionalFoodModel {
    private String name,url,data;

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

    public TraditionalFoodModel(String name, String url, String data) {
        this.name = name;
        this.url = url;
        this.data = data;
    }

    public TraditionalFoodModel(){

    }
}
