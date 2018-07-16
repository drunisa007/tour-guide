package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/15/2018.
 */

public class MarketModel {



    private  String name,url;
    private String data;
    private String lag;
    private String log;
    private String type;
    private String name_bur;
    private String name_chi;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public MarketModel(String name, String url, String data, String lag, String log, String type, String name_bur, String name_chi) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.lag = lag;
        this.log = log;
        this.type = type;
        this.name_bur = name_bur;
        this.name_chi = name_chi;
    }

    public  MarketModel(){

    }
}
