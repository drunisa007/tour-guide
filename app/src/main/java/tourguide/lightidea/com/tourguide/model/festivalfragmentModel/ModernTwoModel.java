package tourguide.lightidea.com.tourguide.model.festivalfragmentModel;

/**
 * Created by Arun on 5/20/2018.
 */


public class ModernTwoModel {
    private String name,url;
    private String data;

    public ModernTwoModel(String name, String url, String data) {
        this.name = name;
        this.url = url;
        this.data = data;
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



    public  ModernTwoModel(){

    }
}
