package tourguide.lightidea.com.tourguide.model.festivalfragmentModel;

/**
 * Created by Arun on 5/20/2018.
 */


public class ModernTwoModel {
    public ModernTwoModel(String location, String time, String month, String date, String data, String url,String name,String lag,String log) {
        this.location = location;
        this.time = time;
        this.month = month;
        this.date = date;
        this.data = data;
        this.url = url;
        this.name = name;
        this.lag = lag;
        this.log = log;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String location,time,month,date;
    private String data;
    private String url;
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

    private String name;




    public  ModernTwoModel(){

    }
}
