package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/15/2018.
 */

public class FamousModel {

    private String name;
    private String url;
    private String data;
    private String famous;


    public String getFamous() {
        return famous;
    }

    public void setFamous(String famous) {
        this.famous = famous;
    }



    public FamousModel(String name, String url, String data,String famous) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.famous=famous;
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




    public FamousModel(){


    }
}
