package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/14/2018.
 */

public class HistoricModel {

    private String name;
    private String url;

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

    public HistoricModel(String name, String url) {
        this.name = name;
        this.url = url;
    }



    public  HistoricModel(){

    }
}
