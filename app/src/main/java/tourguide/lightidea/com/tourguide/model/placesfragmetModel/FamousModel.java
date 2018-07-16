package tourguide.lightidea.com.tourguide.model.placesfragmetModel;

/**
 * Created by USER on 5/15/2018.
 */

public class FamousModel {

    private String name;
    private String url;
    private String data;
    private String famous;
    private String lag,log;
    private String name_bur;
    private String name_chi;
    private String famous_chi,famous_bur;

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

    public String getFamous() {
        return famous;
    }

    public void setFamous(String famous) {
        this.famous = famous;
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

    public String getFamous_chi() {
        return famous_chi;
    }

    public void setFamous_chi(String famous_chi) {
        this.famous_chi = famous_chi;
    }

    public String getFamous_bur() {
        return famous_bur;
    }

    public void setFamous_bur(String famous_bur) {
        this.famous_bur = famous_bur;
    }

    public FamousModel(String name, String url, String data, String famous, String lag, String log, String name_bur, String name_chi, String famous_chi, String famous_bur) {
        this.name = name;
        this.url = url;
        this.data = data;
        this.famous = famous;
        this.lag = lag;
        this.log = log;
        this.name_bur = name_bur;
        this.name_chi = name_chi;
        this.famous_chi = famous_chi;
        this.famous_bur = famous_bur;
    }

    public FamousModel(){


    }
}
