package tourguide.lightidea.com.tourguide.model.festivalfragmentModel;

public class ModernTwoModel {
    private String location,time,month;
    private String time_bur,time_chi;
    private String month_bur,month_chi;
    private String date_bur,date_chi;
    private String data;
    private String url;
    private String lag,log;
    private String name;

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

    public String getTime_bur() {
        return time_bur;
    }

    public void setTime_bur(String time_bur) {
        this.time_bur = time_bur;
    }

    public String getTime_chi() {
        return time_chi;
    }

    public void setTime_chi(String time_chi) {
        this.time_chi = time_chi;
    }

    public String getMonth_bur() {
        return month_bur;
    }

    public void setMonth_bur(String month_bur) {
        this.month_bur = month_bur;
    }

    public String getMonth_chi() {
        return month_chi;
    }

    public void setMonth_chi(String month_chi) {
        this.month_chi = month_chi;
    }

    public String getDate_bur() {
        return date_bur;
    }

    public void setDate_bur(String date_bur) {
        this.date_bur = date_bur;
    }

    public String getDate_chi() {
        return date_chi;
    }

    public void setDate_chi(String date_chi) {
        this.date_chi = date_chi;
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

    public ModernTwoModel(String location, String time, String month, String time_bur, String time_chi, String month_bur, String month_chi, String date_bur, String date_chi, String data, String url, String lag, String log, String name) {
        this.location = location;
        this.time = time;
        this.month = month;
        this.time_bur = time_bur;
        this.time_chi = time_chi;
        this.month_bur = month_bur;
        this.month_chi = month_chi;
        this.date_bur = date_bur;
        this.date_chi = date_chi;
        this.data = data;

        this.url = url;
        this.lag = lag;
        this.log = log;
        this.name = name;
    }

    public ModernTwoModel(){

    }
}
