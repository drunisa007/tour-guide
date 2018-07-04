package tourguide.lightidea.com.tourguide.activity.Hotel;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.model.HotelModel.FacModel;
import tourguide.lightidea.com.tourguide.model.HotelModel.HotelSingleReview;

public class HotelSingleActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Toolbar mToolbar;
    private String hotelName,hotelUrl,hotelData,position;
    private String phone,location,price,id;
    private CardView hotel_single_review_button;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private List<FacModel> mList =new ArrayList<>();

    private RecyclerView mRecycler;


    //data for address and price and room

    private TextView hotelPhone,hotelLocation,hotelPrice,hotelRoom,hotelReroom;

    private RecyclerView hotel_single_review_recyclerview;
    private int RatingOne,RatingTwo,RatingThree,RatingFour,RatingFive;
    private MaterialDialog mDialog;

 private    FirestoreRecyclerAdapter<HotelSingleReview,MyHotelSingleReviewRecyclerAdapter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_single);

        hotelName = getIntent().getStringExtra("name");
        hotelUrl = getIntent().getStringExtra("url");
        hotelData= getIntent().getStringExtra("data");
        position= getIntent().getStringExtra("position");
        phone=getIntent().getStringExtra("phone");
        location=getIntent().getStringExtra("location");
        price=getIntent().getStringExtra("price");
        id = getIntent().getStringExtra("id");
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        //room=getIntent().getStringExtra("room");
        //reroom=getIntent().getStringExtra("reroom");



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        givingId();

        settingDataForHotel();

        settingToolbar();

        initCollapsingToolbar();

        gettingData(hotelData);

        workingforReviewRecyclerView();





    }

    private void workingforReviewRecyclerView() {
        LinearLayoutManager review_manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        hotel_single_review_recyclerview.setLayoutManager(review_manager);
        Query query = FirebaseFirestore.getInstance().collection("Hotels").document(hotelData).collection("data");
        FirestoreRecyclerOptions<HotelSingleReview> options = new FirestoreRecyclerOptions.Builder<HotelSingleReview>()
                .setQuery(query,HotelSingleReview.class).build();

       adapter= new FirestoreRecyclerAdapter<HotelSingleReview, MyHotelSingleReviewRecyclerAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyHotelSingleReviewRecyclerAdapter holder, int position, @NonNull HotelSingleReview model) {
                holder.mReview.setText(model.getReview());
                holder.mRatingBar.setRating(Float.parseFloat(model.getRating().trim()));
                holder.mRatingBar.setEnabled(false);
            }

            @NonNull
            @Override
            public MyHotelSingleReviewRecyclerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_single_review_reccylerlayout,parent,false);
                return new MyHotelSingleReviewRecyclerAdapter(view);
            }
        };
       hotel_single_review_recyclerview.setNestedScrollingEnabled(false);

       hotel_single_review_recyclerview.setAdapter(adapter);

    }


    private class MyHotelSingleReviewRecyclerAdapter extends RecyclerView.ViewHolder {
        RatingBar mRatingBar;
        TextView mReview;
        public MyHotelSingleReviewRecyclerAdapter(View itemView) {
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

    private void settingDataForHotel() {
        hotelPhone.setText(phone);
        hotelLocation.setText(location);
        hotelPrice.setText(price);
        //hotelRoom.setText(room);
       // hotelReroom.setText(reroom);
    }


    private void gettingData(String hotelData) {

        FirebaseFirestoreSettings settings=new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        db.setFirestoreSettings(settings);

        CollectionReference reference= db.collection("hotel_about").document("data").collection(hotelData);

        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(DocumentSnapshot documentSnapshot:task.getResult()){
                        FacModel model = documentSnapshot.toObject(FacModel.class);
                        mList.add(model);
                        workingForRecylcerView(mList);
                    }
                } else {
                    Log.d("arun", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void workingForRecylcerView(List<FacModel> mList) {

        List<Boolean> booleanList=new ArrayList<>();
        booleanList.add(mList.get(0).getDinner());
        booleanList.add(mList.get(0).getWifi());
        booleanList.add(mList.get(0).getPool());
        booleanList.add(mList.get(0).getPet());
        booleanList.add(mList.get(0).getFit());
        booleanList.add(mList.get(0).getBar());
        booleanList.add(mList.get(0).getBaby());
        booleanList.add(mList.get(0).getCar());
        booleanList.add(mList.get(0).getBike());
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecycler.setLayoutManager(manager);

        mRecycler.setHasFixedSize(true);
        List<Integer> imageList = new ArrayList();
        imageList.add(R.drawable.dinner);
        imageList.add(R.drawable.wifi);
        imageList.add(R.drawable.pool);
        imageList.add(R.drawable.pet);
        imageList.add(R.drawable.fit);
        imageList.add(R.drawable.bar);
        imageList.add(R.drawable.baby);
        imageList.add(R.drawable.car);
        imageList.add(R.drawable.bike);
        List<Integer> DisimageList = new ArrayList();
        DisimageList.add(R.drawable.disdinner);
        DisimageList.add(R.drawable.diswifi);
        DisimageList.add(R.drawable.dispool);
        DisimageList.add(R.drawable.dispet);
        DisimageList.add(R.drawable.disfit);
        DisimageList.add(R.drawable.disbar);
        DisimageList.add(R.drawable.disbaby);
        DisimageList.add(R.drawable.discar);
        DisimageList.add(R.drawable.disbike);
        List<String> toastList=new ArrayList<>();
        toastList.add("You can have dinner and breakfast in this hotel.");
        toastList.add("Free Wifi  available in all Rooms");
        toastList.add("You can relax in the swimming pool. ");
        toastList.add("You can carry your pets in this hotel.");
        toastList.add("Fitness Center for you.");
        toastList.add("A standard Bar for you");
        toastList.add("Baby cot free of charge -necessary to reserve in advance.");
        toastList.add("You can hire Cars");
        toastList.add("You can hire Bikes");
        CoordinatorLayout coor=findViewById(R.id.hotel_single_coor);
        mRecycler.setNestedScrollingEnabled(true);

        mRecycler.setAdapter(new MyHotelSingleRecyclerAdapter(this,booleanList,imageList,DisimageList,toastList,coor));
    }



    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.hotel_single_collapsing_toolbar);
        collapsingToolbar.setTitle(hotelName);
        AppBarLayout appBarLayout = findViewById(R.id.hotel_single_appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
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

    private void givingId() {
        mToolbar = findViewById(R.id.hotel_single_toolbar);
        mImageView = findViewById(R.id.hotel_single_imageview);
        mRecycler =findViewById(R.id.hotel_single_recyclerview);
        Glide.with(this).load(hotelUrl).into(mImageView);

        hotelPhone = findViewById(R.id.hotel_single_phone);
        hotelLocation = findViewById(R.id.hotel_single_location);
        hotelPrice = findViewById(R.id.hotel_single_price);
       // hotelRoom = findViewById(R.id.hotel_single_room);
       // hotelReroom = findViewById(R.id.hotel_single_reroom);

        hotel_single_review_recyclerview = findViewById(R.id.hotel_single_review_recyclerview);

        hotel_single_review_button= findViewById(R.id.hotel_single_review_button);
        hotel_single_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showingDialog();
            }
        });





    }

    private void showingDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .customView(R.layout.review_layout_for_hotel,false);

          mDialog = builder.build();
        View view= mDialog.getView();
        final RatingBar ratingBar  = view.findViewById(R.id.review_ratingBar);
        final EditText editText  = view.findViewById(R.id.review_edittext);
        CardView cardView  = view.findViewById(R.id.review_cardview);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String rate=String.valueOf(ratingBar.getRating());
                String review=editText.getText().toString();
                if(rate.equals("1.0")){
                    puttingData("one",rate,review);
                }
                else if(rate.equals("2.0")){
                    puttingData("two",rate,review);
                }
                else if(rate.equals("3.0")){
                  puttingData("three",rate,review);
                }
                else if(rate.equals("4.0")){
                   puttingData("four",rate,review);
                }
                else if(rate.equals("5.0")){
                      puttingData("five",rate,review);
                }


            }
        });
        mDialog.show();
    }

    private void puttingData(String one, final String rate, final String review) {
        Map<String,String> map = new HashMap<>();
        map.put(one,one);
        FirebaseFirestore
                .getInstance()
                .collection("HotelRating")
                .document(hotelData).collection(one).add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    gettingRatingData(rate,review);
                }
            }
        });

    }

    private void gettingRatingData(final String rate, final String review) {


        FirebaseFirestore.getInstance().collection("HotelRating").document(hotelData).collection("one").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        RatingOne= task.getResult().size();
                        FirebaseFirestore
                                .getInstance()
                                .collection("HotelRating")
                                .document(hotelData).collection("two").get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        RatingTwo= task.getResult().size();
                                        FirebaseFirestore
                                                .getInstance()
                                                .collection("HotelRating")
                                                .document(hotelData).collection("three").get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        RatingThree= task.getResult().size();
                                                        FirebaseFirestore
                                                                .getInstance()
                                                                .collection("HotelRating")
                                                                .document(hotelData).collection("four").get()
                                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                        RatingFour= task.getResult().size();

                                                                    }
                                                                });
                                                        FirebaseFirestore
                                                                .getInstance()
                                                                .collection("HotelRating")
                                                                .document(hotelData).collection("five").get()
                                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                        RatingFive= task.getResult().size();
                                                                      calculatingDataAndSettingData(rate,review);
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                    }
                });


    }

    private void calculatingDataAndSettingData(final String rate, final String review) {
        double x1 = RatingOne*1;
        double x2 = RatingTwo*2;
        double x3= RatingThree*3;
        double x4= RatingFour*4;
        double x5 = RatingFive*5;
        double XX =x1+x2+x3+x4+x5;
        double X  =RatingOne+RatingTwo+RatingThree+RatingFour+RatingFive;

        double xxResult = Double.parseDouble(String.valueOf(XX));
        double bigResult = xxResult/X;
        DecimalFormat format  = new DecimalFormat("#0.0");
        String finalResult =format.format(bigResult);

        if(rate.equals("0.0")){
            Toast.makeText(HotelSingleActivity.this, "To give a Review you need to give at least one star", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(review)){
            Toast.makeText(HotelSingleActivity.this, "Review can not be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Map<String,String> newMap = new HashMap<>();
            newMap.put("rating",finalResult);
            FirebaseFirestore.getInstance().collection("Hotel").document(id).set(newMap,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Map<String,String>  map  = new HashMap<>();
                    map.put("rating",rate);
                    map.put("review",review);
                    FirebaseFirestore.getInstance().collection("Hotels").document(hotelData).collection("data").add(map)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(HotelSingleActivity.this, "Review has been written.", Toast.LENGTH_SHORT).show();
                                    mDialog.dismiss();
                                }
                            });

                }
            });


        }
    }


    private void showingFab(String title) {
        String   message = title;
        CoordinatorLayout coor=findViewById(R.id.hotel_single_coor);

        Snackbar snackbar = Snackbar.make(coor, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextSize(17);
        textView.setTextColor(Color.parseColor("#ffffff"));
        snackbar.show();
    }


}

