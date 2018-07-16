package tourguide.lightidea.com.tourguide.model.PlaceSingleFragmentModel;

/**
 * Created by USER on 5/22/2018.
 */

public class AboutOneModel {

    private String title,body,image;
    private String title_bur,title_chi;
    private String body_bur,body_chi;

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

    public String getTitle_bur() {
        return title_bur;
    }

    public void setTitle_bur(String title_bur) {
        this.title_bur = title_bur;
    }

    public String getTitle_chi() {
        return title_chi;
    }

    public void setTitle_chi(String title_chi) {
        this.title_chi = title_chi;
    }

    public String getBody_bur() {
        return body_bur;
    }

    public void setBody_bur(String body_bur) {
        this.body_bur = body_bur;
    }

    public String getBody_chi() {
        return body_chi;
    }

    public void setBody_chi(String body_chi) {
        this.body_chi = body_chi;
    }

    public AboutOneModel(String title, String body, String image, String title_bur, String title_chi, String body_bur, String body_chi) {
        this.title = title;
        this.body = body;
        this.image = image;
        this.title_bur = title_bur;
        this.title_chi = title_chi;
        this.body_bur = body_bur;
        this.body_chi = body_chi;
    }

    public AboutOneModel(){

    }
}
