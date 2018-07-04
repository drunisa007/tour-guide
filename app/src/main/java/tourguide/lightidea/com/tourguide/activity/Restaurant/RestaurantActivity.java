package tourguide.lightidea.com.tourguide.activity.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tourguide.lightidea.com.tourguide.R;

public class RestaurantActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CardView traditionalFood,asianFood,europeanfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        givingId();

        settingToolbar();


    }

    private void settingToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Restaurant");
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
                intent.putExtra("title","Traditional Food");
                intent.putExtra("all","all");
                startActivity(intent);
            }
        });


        asianFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                intent.putExtra("data","asianfood");
                intent.putExtra("title","Asian  Food");
                intent.putExtra("all","all");
                startActivity(intent);
            }
        });

        europeanfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                intent.putExtra("data","europeanfood");
                intent.putExtra("title","European Food");
                intent.putExtra("all","all");
                startActivity(intent);
            }
        });
    }

    private void givingId() {
        mToolbar = findViewById(R.id.restaurant_toolbar);
        traditionalFood = findViewById(R.id.traditional_food_cardview);
        asianFood = findViewById(R.id.asian_food_cardview);
        europeanfood = findViewById(R.id.european_food_cardview);

    }
}
