package tourguide.lightidea.com.tourguide.model.PlaceSingleFragmentModel;

/**
 * Created by USER on 5/22/2018.
 */

public class AboutOneModel {

    private String title,body,image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AboutOneModel(String title, String body, String image) {
        this.title = title;
        this.body = body;
        this.image = image;
    }

    public AboutOneModel(){

    }
}
