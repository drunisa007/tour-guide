package tourguide.lightidea.com.tourguide.activity.PlaceActivityPg;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.adapter.viewpageAdapter.MyPlaceViewpagerAdapter;
import tourguide.lightidea.com.tourguide.fragment.s.placesFragment.famousThree;
import tourguide.lightidea.com.tourguide.fragment.s.placesFragment.historicTwo;
import tourguide.lightidea.com.tourguide.fragment.s.placesFragment.marketFive;
import tourguide.lightidea.com.tourguide.fragment.s.placesFragment.pagodaOne;
import tourguide.lightidea.com.tourguide.fragment.s.placesFragment.resortFour;

public class PlaceActiviy extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTablayout;
    private ViewPager mViewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        givingId();

        settingToolbar();

        settingTabLayout();
    }





    //giving Id
    private void givingId() {
        mToolbar = findViewById(R.id.place_toolbar);
        mTablayout = findViewById(R.id.place_tab);
        mViewpager = findViewById(R.id.place_viewpager);
    }

    private  void settingToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Places");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void settingTabLayout() {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("Pagoda");
        listTitle.add("Historic");
        listTitle.add("Famous");
        listTitle.add("Resorts");
        listTitle.add("Markets");
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new pagodaOne());
        listFragment.add(new historicTwo());
        listFragment.add(new famousThree());
        listFragment.add(new resortFour());
        listFragment.add(new marketFive());
        mViewpager.setAdapter(new MyPlaceViewpagerAdapter(this,getSupportFragmentManager(),listTitle,listFragment));
        mViewpager.setOffscreenPageLimit(5);
        mTablayout.setupWithViewPager(mViewpager);
    }
}

