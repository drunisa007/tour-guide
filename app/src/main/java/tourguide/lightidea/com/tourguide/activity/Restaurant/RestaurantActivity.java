package tourguide.lightidea.com.tourguide.activity.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import tourguide.lightidea.com.tourguide.R;

public class RestaurantActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CardView traditionalFood,asianFood,europeanfood;
    private String data;
    private String title;
    private String foodOne,foodTwo,foodThree;
    private TextView foodViewOne,foodViewTwo,foodViewThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        data = getIntent().getStringExtra("data");


        if(data.equals("eng")){
            title="Hotel";
            foodOne="Traditional Food";
            foodTwo="Asian Food";
            foodThree="European Food";
        }
        else if(data.equals("bur")){
            title="ေမာင္ေသ္ာင္သူ";
            foodOne="Traditional FoodB";
            foodTwo="Asian FoodB";
            foodThree="European FoodB";
        }
        else{
            title="Hotel_C";
            foodOne="Traditional FoodC";
            foodTwo="Asian FoodC";
            foodThree="European FoodC";
        }

        givingId();

        settingToolbar();

        foodViewOne.setText(foodOne);
        foodViewTwo.setText(foodTwo);
        foodViewThree.setText(foodThree);


    }

    private void settingToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 onBackPressed();
            }
        });

        traditionalFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                intent.putExtra("data","traditionalfood");
                intent.putExtra("title",foodOne);
                intent.putExtra("all","all");
                intent.putExtra("language",data);
                startActivity(intent);
            }
        });


        asianFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                intent.putExtra("data","asianfood");
                intent.putExtra("title",foodTwo);
                intent.putExtra("all","all");
                intent.putExtra("language",data);
                startActivity(intent);
            }
        });

        europeanfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                intent.putExtra("data","europeanfood");
                intent.putExtra("title",foodThree);
                intent.putExtra("all","all");
                intent.putExtra("language",data);
                startActivity(intent);
            }
        });
    }

    private void givingId() {
        mToolbar = findViewById(R.id.restaurant_toolbar);
        traditionalFood = findViewById(R.id.traditional_food_cardview);
        asianFood = findViewById(R.id.asian_food_cardview);
        europeanfood = findViewById(R.id.european_food_cardview);

        foodViewOne=findViewById(R.id.restaurant_food_one);

        foodViewTwo= findViewById(R.id.restaurant_food_two);
        foodViewThree=findViewById(R.id.restaurant_food_three);

    }
}
