package tourguide.lightidea.com.tourguide.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.model.RestaurantModel.TraditionalFoodModel;

public class RestaurantSingleActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private String data,title;

    private  FirestoreRecyclerAdapter<TraditionalFoodModel,MyTraditionalFoodViewHolder>  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tradition_food_single);

        data= getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");

        
        givingId();

        settingToolbar();
        
        workingForRecyclerView();
    }

    private void settingToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void workingForRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setHasFixedSize(true);
        Query query = FirebaseFirestore.getInstance().collection("Restaurant").document(data).collection("data");
        FirestoreRecyclerOptions<TraditionalFoodModel> options = new FirestoreRecyclerOptions.
                Builder<tourguide.lightidea.com.tourguide.model.RestaurantModel.TraditionalFoodModel>()
                .setQuery(query,TraditionalFoodModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<TraditionalFoodModel, MyTraditionalFoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyTraditionalFoodViewHolder holder, int position, @NonNull TraditionalFoodModel model) {
                Glide.with(RestaurantSingleActivity.this).load(model.getUrl()).into(holder.foodImage);
                holder.foodName.setText(model.getName());

            }

            @NonNull
            @Override
            public MyTraditionalFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view=getLayoutInflater().inflate(R.layout.traditional_single_recycler_layout,parent,false);
                return new MyTraditionalFoodViewHolder(view);
            }
        };
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    private class MyTraditionalFoodViewHolder extends RecyclerView.ViewHolder {
        ImageView  foodImage;
        TextView foodName;
        public MyTraditionalFoodViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.traditional_single_food_image);
            foodName = itemView.findViewById(R.id.traditional_single_foodname);
        }
    }

    private void givingId() {
        mToolbar = findViewById(R.id.traditional_food_single_toolbar);


        mRecyclerView = findViewById(R.id.traditional_food_single_recyclerview);
        
    }


}
