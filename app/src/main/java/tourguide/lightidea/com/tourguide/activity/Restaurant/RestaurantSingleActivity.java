package tourguide.lightidea.com.tourguide.activity.Restaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.MainAndOther.MainActivity;
import tourguide.lightidea.com.tourguide.adapter.DialogAdapter.DialogAdapter;
import tourguide.lightidea.com.tourguide.model.RestaurantModel.TraditionalFoodModel;

public class RestaurantSingleActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private String data,title,all;

    private  FirestoreRecyclerAdapter<TraditionalFoodModel,MyTraditionalFoodViewHolder>  adapter;

    private Query queryOne;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tradition_food_single);

        data= getIntent().getStringExtra("data");
        title = getIntent().getStringExtra("title");
        all = getIntent().getStringExtra("all");

        language = getIntent().getStringExtra("language");

        
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
        if(all.equals("all")){
             queryOne = FirebaseFirestore.getInstance().collection("Restaurant").document(data).collection("data").document("all").collection("data");

        }
        else{
             queryOne = FirebaseFirestore.getInstance().collection("Restaurant").document(data).collection("data").document("all").collection("data").whereEqualTo("type",all);

        }
        FirestoreRecyclerOptions<TraditionalFoodModel> options = new FirestoreRecyclerOptions.
                Builder<tourguide.lightidea.com.tourguide.model.RestaurantModel.TraditionalFoodModel>()
                .setQuery(queryOne,TraditionalFoodModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<TraditionalFoodModel, MyTraditionalFoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyTraditionalFoodViewHolder holder, int position, @NonNull final TraditionalFoodModel model) {
                Glide.with(RestaurantSingleActivity.this).load(model.getUrl()).into(holder.foodImage);
                final String name,phone,address;
                if(language.equals("eng")){
                    name=model.getName();
                    phone=model.getPhone();
                    address=model.getAddress();
                }
                else if(language.equals("bur")){
                    name=model.getName_bur();
                    phone=model.getPhone_bur();
                    address=model.getAddress_bur();
                }
                else{
                    name=model.getName_chi();
                    phone=model.getPhone_chi();
                    address=model.getAddress_chi();
                }
                holder.foodName.setText(name);
                holder.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent  = new Intent(RestaurantSingleActivity.this,FoodSingleActiivity.class);
                        intent.putExtra("title",name);
                        intent.putExtra("park",model.getPark());
                        intent.putExtra("book",model.getBook());
                        intent.putExtra("phone",phone);
                        intent.putExtra("address",address);
                        intent.putExtra("url",model.getUrl());
                        intent.putExtra("data",model.getData());
                        startActivity(intent);
                    }
                });

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
        CardView mCardView;
        public MyTraditionalFoodViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.traditional_single_food_image);
            foodName = itemView.findViewById(R.id.traditional_single_foodname);
            mCardView= itemView.findViewById(R.id.traditional_single_food_cardview);
        }
    }

    private void givingId() {
        mToolbar = findViewById(R.id.traditional_food_single_toolbar);


        mRecyclerView = findViewById(R.id.traditional_food_single_recyclerview);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.restaurant_menu,menu);
        MenuItem item = menu.findItem(R.id.restaurant_menuId);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.restaurant_menuId){
            showDialog();
        }
        return true;
    }

    private void showDialog() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_layout,false);

        MaterialDialog mDialog = builder.build();

        View view= mDialog.getView();



       TextView textView = view.findViewById(R.id.dialog_single_textvewi);
       textView.setText("Choose Food Type");
        RecyclerView dialog_recyclerview = view.findViewById(R.id.dialog_recyclerview);
        dialog_recyclerview.setHasFixedSize(true);
        dialog_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        List<String> list = new ArrayList<>();
        if(data.equals("traditionalfood")){
            list.add("Burmese");
            list.add("Shan");
        }
        else if(data.equals("asianfood")){
            list.add("Chinese");
            list.add("Thai");
            list.add("Indian");
            list.add("Korean");
            list.add("Japanese");

        }
        else if(data.equals("europeanfood")){
            list.add("European");
            list.add("Italian");
            list.add("French");
        }

        List mList = new ArrayList();
        mList.add(RestaurantSingleActivity.class);
        mList.add(MainActivity.class);
        dialog_recyclerview.setAdapter(new DialogAdapter(list,this,0,mList,data,title,language));
        mDialog.show();
    }
}
