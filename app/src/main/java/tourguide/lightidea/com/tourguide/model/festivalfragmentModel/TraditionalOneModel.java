package tourguide.lightidea.com.tourguide.model.festivalfragmentModel;

/**
 * Created by Arun on 5/19/2018.
 */

public class TraditionalOneModel {

    public  TraditionalOneModel(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String title;
    private String day;
    private String name;
    private String data;


    public TraditionalOneModel(String title, String day, String name, String data) {
        this.title = title;
        this.day = day;
        this.name = name;
        this.data = data;
    }






}