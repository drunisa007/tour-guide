package tourguide.lightidea.com.tourguide.model.RestaurantModel;

public class FoodReviewModel {
    private String rating,review;

    public FoodReviewModel(String rating, String review) {
        this.rating = rating;
        this.review = review;
    }

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

    public FoodReviewModel(){

    }
}
