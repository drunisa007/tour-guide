package tourguide.lightidea.com.tourguide.activity.Place;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        data = getIntent().getStringExtra("data");

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
        if(data.equals("eng")){
            getSupportActionBar().setTitle("Places");

        }
        else if(data.equals("bur")){
            getSupportActionBar().setTitle("ေညာင္");

        }
        else{
            getSupportActionBar().setTitle("Places_C");

        }
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
        List<String> listTitle_bur=new ArrayList<>();
        listTitle_bur.add("Pagoda_B");
        listTitle_bur.add("Historic_B");
        listTitle_bur.add("Famous_B");
        listTitle_bur.add("Resorts_B");
        listTitle_bur.add("Markets_B");
        List<String> listTitle_chi=new ArrayList<>();
        listTitle_chi.add("Pagoda_C");
        listTitle_chi.add("Historic_C");
        listTitle_chi.add("Famous_C");
        listTitle_chi.add("Resorts_C");
        listTitle_chi.add("Markets_C");
        Bundle bd =new Bundle();
        bd.putString("language",data);
        pagodaOne pagodaOne = new pagodaOne();
        pagodaOne.setArguments(bd);
        historicTwo historicTwo =new historicTwo();
        historicTwo.setArguments(bd);
        famousThree famousThree =new famousThree();
        famousThree.setArguments(bd);
        resortFour resortFour = new resortFour();
        resortFour.setArguments(bd);
        marketFive marketFive = new marketFive();
        marketFive.setArguments(bd);
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(pagodaOne);
        listFragment.add(historicTwo);
        listFragment.add(famousThree);
        listFragment.add(resortFour);
        listFragment.add(marketFive);
        if(data.equals("eng")){
            mViewpager.setAdapter(new MyPlaceViewpagerAdapter(this,getSupportFragmentManager(),listTitle,listFragment));

        }
        else if(data.equals("bur")){
            mViewpager.setAdapter(new MyPlaceViewpagerAdapter(this,getSupportFragmentManager(),listTitle_bur,listFragment));

        }
        else{
            mViewpager.setAdapter(new MyPlaceViewpagerAdapter(this,getSupportFragmentManager(),listTitle_chi,listFragment));

        }
        mViewpager.setOffscreenPageLimit(5);
        mTablayout.setupWithViewPager(mViewpager);
    }
}

