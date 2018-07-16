package tourguide.lightidea.com.tourguide.activity.Restaurant;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.Hotel.HotelSingleActivity;
import tourguide.lightidea.com.tourguide.model.RestaurantModel.FoodReviewModel;

public class FoodSingleActiivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mImageView;
    private CoordinatorLayout mCoor;
    private AppBarLayout mAppbar;

    private String hotelName;
    //CardView Parking and Booking
    private TextView parking_text,booking_text;
    private ImageView parking_image,booking_image;

    private TextView food_phone,food_address;
    //about id
    private RecyclerView food_review_recyclerview;
    private CardView food_review_button;


    private String  text_park,text_book;
    private String text_phone,text_address;

    private String restData,restUrl;

    private FirestoreRecyclerAdapter<FoodReviewModel,MyFoodSingleReviewHolder>  adapter ;

    private MaterialDialog mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_single_actiivity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        hotelName= getIntent().getStringExtra("title");
        gettingData();



        givingId();

        settingToolbar();

        initCollapsingToolbar();

        settingDataForFoodSingleLayout();


        food_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });

        Glide.with(this).load(restUrl).into(mImageView);





    }

    private void displayDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .customView(R.layout.review_layout_for_hotel,false);

        final MaterialDialog mDialog = builder.build();
        View view= mDialog.getView();
        final RatingBar ratingBar  = view.findViewById(R.id.review_ratingBar);
        final EditText editText  = view.findViewById(R.id.review_edittext);
        CardView cardView  = view.findViewById(R.id.review_cardview);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate=String.valueOf(ratingBar.getRating());
                String review=editText.getText().toString();
                if(rate.equals("0.0")){
                    Toast.makeText(FoodSingleActiivity.this, "To give a Review you need to give at least one star", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(review)){
                    Toast.makeText(FoodSingleActiivity.this, "Review can not be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    mLoadingDialog = new MaterialDialog.Builder(FoodSingleActiivity.this)
                            .title("Please Wait...")
                            .content("Posting Your Review . . .")
                            .progress(true,0)
                            .canceledOnTouchOutside(false)
                            .build();

                    mLoadingDialog.show();
                    Map<String,String> map  = new HashMap<>();
                    map.put("rating",rate);
                    map.put("review",review);
                    FirebaseFirestore.getInstance().collection("Restaurants").document(restData).collection("data").add(map)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(FoodSingleActiivity.this, "Review has been Posted.", Toast.LENGTH_SHORT).show();
                                   mLoadingDialog.dismiss();
                                    mDialog.dismiss();

                                }
                            });
                }
            }
        });
        mDialog.show();
    }

    private void gettingData() {
        text_park = getIntent().getStringExtra("park");
        text_book = getIntent().getStringExtra("book");
        text_phone = getIntent().getStringExtra("phone");
        text_address = getIntent().getStringExtra("address");
        restData = getIntent().getStringExtra("data");
        restUrl = getIntent().getStringExtra("url");
    }

    private void settingDataForFoodSingleLayout() {

        if(!TextUtils.isEmpty(text_park)){
            if(text_park.equals("park")){
                parking_text.setText("YES");
                Glide.with(this).load(R.drawable.ic_action_check).into(parking_image);
            }
            else{
                parking_text.setText("NO");
                Glide.with(this).load(R.drawable.ic_action_clear).into(parking_image);
            }
        }
        else{
            parking_text.setText("NO");
            Glide.with(this).load(R.drawable.ic_action_clear).into(parking_image);
        }



        if(!TextUtils.isEmpty(text_book)){
            if(text_book.equals("book")){
                booking_text.setText("YES");
                Glide.with(this).load(R.drawable.ic_action_check).into(booking_image);
            }
            else{
                booking_text.setText("NO");
                Glide.with(this).load(R.drawable.ic_action_clear).into(booking_image);
            }
        }
        else{

            booking_text.setText("NO");
            Glide.with(this).load(R.drawable.ic_action_clear).into(booking_image);
        }


        food_phone.setText(text_phone);
        food_address.setText(text_address);

        settingRecyclerViewForReview();

    }

    private void settingRecyclerViewForReview() {

        LinearLayoutManager review_manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        food_review_recyclerview.setLayoutManager(review_manager);
        Query query = FirebaseFirestore.getInstance().collection("Restaurants").document(restData).collection("data");
        FirestoreRecyclerOptions<FoodReviewModel> options = new FirestoreRecyclerOptions.Builder<FoodReviewModel>()
                .setQuery(query,FoodReviewModel.class).build();

       adapter= new FirestoreRecyclerAdapter<FoodReviewModel, MyFoodSingleReviewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyFoodSingleReviewHolder holder, int position, @NonNull FoodReviewModel model) {
                holder.mReview.setText(model.getReview());
                holder.mRatingBar.setRating(Float.parseFloat(model.getRating().trim()));
                holder.mRatingBar.setEnabled(false);
            }

            @NonNull
            @Override
            public MyFoodSingleReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_single_review_reccylerlayout,parent,false);
                return new MyFoodSingleReviewHolder(view)   ;
            }
        };


        food_review_recyclerview.setNestedScrollingEnabled(false);

        food_review_recyclerview.setAdapter(adapter);

    }

    private void givingId() {
        mToolbar = findViewById(R.id.food_single_toolbar);
        mImageView = findViewById(R.id.food_single_imageview);
        mCoor = findViewById(R.id.food_single_coor);
        mAppbar = findViewById(R.id.food_single_appbar);

        //giving Id of text

        parking_text = findViewById(R.id.food_parking_text);
        booking_text = findViewById(R.id.food_booking_text);

        //giving id of image

        parking_image = findViewById(R.id.food_parking_image);
        booking_image  = findViewById(R.id.food_booking_image);

        //giving id of food phone and address

        food_phone = findViewById(R.id.food_phone);
        food_address  = findViewById(R.id.food_address);

        //gicing id for reviewreccylerview and review giving button

        food_review_button = findViewById(R.id.food_review_button);

        food_review_recyclerview = findViewById(R.id.food_review_recyclerview);


    }

    private void settingToolbar() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.food_single_collapsing_toolbar);
        collapsingToolbar.setTitle(hotelName);
        mAppbar.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(hotelName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(hotelName);
                    isShow = false;
                }
            }
        });
    }


    private class MyFoodSingleReviewHolder extends RecyclerView.ViewHolder {
        RatingBar mRatingBar;
        TextView mReview;
        public MyFoodSingleReviewHolder(View itemView) {
            super(itemView);
            mRatingBar =itemView.findViewById(R.id.hotel_single_review_rating_bar);
            mReview= itemView.findViewById(R.id.hotel_single_review_text);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
