package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/15/2018.
 */

public class ResortModel {
    private String name,url;
    private String data;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public ResortModel(String name, String url, String data,String rating) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.rating= rating;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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



    public ResortModel(){

    }
}
