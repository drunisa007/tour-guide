package tourguide.lightidea.com.tourguide.activity.Place;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import tourguide.lightidea.com.tourguide.R;

public class PlaceRecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_recycler);

    }
}
