package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/13/2018.
 */

public class PagodaModel {

    public PagodaModel(String data, String url, String name,String lag,String log) {
        this.data = data;
        this.url = url;
        this.name = name;
        this.lag  = lag;
        this.log = log;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    private String url;
    private String name;
    private String lag,log;

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


    public PagodaModel(){

    }

}
