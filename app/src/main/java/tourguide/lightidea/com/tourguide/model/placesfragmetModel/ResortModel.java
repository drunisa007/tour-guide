package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/15/2018.
 */

public class ResortModel {
    private String name,url;
    private String data;
    private String lag;
    private String log;
    private String name_bur;

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

    private String name_chi;

    public String getLag() {
        return lag;
    }

    public void setLag(String lag) {
        this.lag = lag;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }



    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public ResortModel(String name, String url, String data,String rating,String lag,String log,String name_bur,String name_chi) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.rating= rating;
        this.lag =lag;
        this.log = log;
        this.name_bur  =name_bur;
        this.name_chi = name_chi;
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
