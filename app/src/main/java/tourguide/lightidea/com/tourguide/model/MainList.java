package tourguide.lightidea.com.tourguide.model;

/**
 * Created by USER on 5/11/2018.
 */

public class MainList {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public MainList(String name, int url) {
        this.name = name;
        this.url = url;
    }

    private String name;
    private int url;
    public MainList(){

    }





}
