package tourguide.lightidea.com.tourguide.model.HotelModel;

public class HotelSingleReview{
    private String rating,review;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public HotelSingleReview(String rating, String review) {
        this.rating = rating;
        this.review = review;
    }

    public HotelSingleReview(){

    }

}
