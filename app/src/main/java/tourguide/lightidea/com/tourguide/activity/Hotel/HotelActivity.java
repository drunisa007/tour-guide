package tourguide.lightidea.com.tourguide.activity.Hotel;

import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.model.HotelModel.HotelModel;

public class HotelActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private Toolbar mToolbar;

    private FirestoreRecyclerAdapter<HotelModel,MyHotelViewHolder> adapter;

    private Query q;

    private TextView mTextNOFound;
    private String data;
/*    5 stars is 250
            4 stars is 400
            3 stars is 20
            2 stars is 400
            1 star is 30

            (5*250+4*400+3*20+2*400+1*30)/(250+400+20+400+30)=result;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        data = getIntent().getStringExtra("data");
        String title;
        if(data.equals("eng")){
            title="hotel";
        }
        else if(data.equals("bur")){
            title="??????????????????????????????";
        }
        else{
            title="??????";
        }

        mRecyclerView = findViewById(R.id.hotel_recyclerview);
        mToolbar = findViewById(R.id.hotel_toolbar);
        mTextNOFound = findViewById(R.id.hotel_no_found_textview_);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        workingForRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hotel_menu,menu);
        MenuItem item = menu.findItem(R.id.hotel_menuId);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        ///reqweerter
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                String newText = text.toLowerCase().trim();
                boolean newBol = newText.matches("\\w*");
                if(newBol){
                    q=FirebaseFirestore.getInstance().collection("Hotel").whereEqualTo("data",newText);

                }
                else{
                    String noWhiteText=newText;
                    noWhiteText=noWhiteText.replaceAll("\\s+","");
                    q=FirebaseFirestore.getInstance().collection("Hotel").whereEqualTo("data",noWhiteText);

                }

                q.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){
                            mTextNOFound.setVisibility(View.VISIBLE);
                        }
                        else{
                            mTextNOFound.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                if(!text.trim().isEmpty()){
                    recycler(q);
                    adapter.startListening();
                }




                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               if(newText.trim().isEmpty()){
                   q=FirebaseFirestore.getInstance().collection("Hotel");
                   mTextNOFound.setVisibility(View.INVISIBLE);
                   recycler(q);
                   adapter.startListening();

               }

                return false;
            }
        });

        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private void workingForRecyclerView() {
        q = FirebaseFirestore.getInstance().collection("Hotel");
        recycler(q);
    }

    private void recycler(Query query) {

        FirestoreRecyclerOptions<HotelModel> options = new FirestoreRecyclerOptions
                .Builder<HotelModel>().setQuery(query,HotelModel.class).build();

        adapter = new FirestoreRecyclerAdapter<HotelModel, MyHotelViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyHotelViewHolder holder, final int position, @NonNull final HotelModel model) {
               final String name,phone,location,price,room,reroom;
                if(data.equals("eng")){
                    holder.mTextViewEng.setText(model.getName());
                    name=model.getName();
                    phone=model.getPhone();
                    location=model.getLocation();
                    price=model.getPrice();
                    room=model.getRoom();
                    reroom=model.getReroom();
                }

                else if(data.equals("bur")){
                    holder.mTextViewEng.setText(model.getName_bur());
                    name=model.getName_bur();
                    phone=model.getPhone_bur();
                    location=model.getLocation_bur();
                    price=model.getPrice_bur();
                    room=model.getRoom_bur();
                    reroom=model.getReroom_bur();
                }
                else {
                    holder.mTextViewEng.setText(model.getName_chi());
                    name=model.getName_chi();
                    phone=model.getPhone_chi();
                    location=model.getLocation_chi();
                    price=model.getPrice_chi();
                    room=model.getRoom_chi();
                    reroom=model.getReroom_chi();
                }
                Glide.with(HotelActivity.this).load(model.getUrl()).into(holder.mImageView);
                holder.textRating.setText(model.getRating());
                holder.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                        Intent intent = new Intent(view.getContext(), HotelSingleActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("url",model.getUrl());
                        intent.putExtra("pos",position);
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","hotel_about");
                        intent.putExtra("phone",phone);
                        intent.putExtra("location",location);
                        intent.putExtra("price",price);
                        intent.putExtra("room",room);
                        intent.putExtra("reroom",reroom);
                        intent.putExtra("id",String.valueOf(snapshot.getId()));
                        view.getContext().startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_recycler_layout,parent,false);
                return new MyHotelViewHolder(view);
            }
        };
        adapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(adapter);
    }


    private class MyHotelViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTextViewEng,mTextViewBur;
        ImageView mImageView;
        TextView textRating;
        public MyHotelViewHolder(View itemView) {
            super(itemView);
            mCardView= itemView.findViewById(R.id.hotel_cardView);
            mTextViewEng =itemView.findViewById(R.id.hotel_textview_eng);
            mImageView = itemView.findViewById(R.id.hotel_imageview);
            textRating = itemView.findViewById(R.id.hotel_rating);
        }
    }
}
