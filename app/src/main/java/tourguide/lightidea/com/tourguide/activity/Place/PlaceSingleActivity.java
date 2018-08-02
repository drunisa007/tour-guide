package tourguide.lightidea.com.tourguide.activity.Place;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.fragment.s.PlaceSingleFragment.AboutOne;
import tourguide.lightidea.com.tourguide.fragment.s.PlaceSingleFragment.LocationTwo;

public class PlaceSingleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String name,data,position;
    private List<String> mListTitle;
    private List<Fragment> mListFragment;
    private String pos;
    private String lag,log;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_single);

      name =  getIntent().getStringExtra("name");
      data = getIntent().getStringExtra("data");
      pos  = getIntent().getStringExtra("pos");
      position=getIntent().getStringExtra("position");
      lag = getIntent().getStringExtra("lag");
      log = getIntent().getStringExtra("log");
      language = getIntent().getStringExtra("language");

        givingId();

        settingToolbar();

        settingData();

        settingTabLayout();
    }




    private void settingData() {
        mListTitle = new ArrayList<>();
        mListFragment = new ArrayList<>();
        mListTitle.add("About");
        mListTitle.add("Location");
        AboutOne one = new AboutOne();
        Bundle bd =new Bundle();
        bd.putString("data",data);
        bd.putString("position",position);
        bd.putString("pos",pos);
        bd.putString("lag",lag);
        bd.putString("log",log);
        bd.putString("language",language);
        one.setArguments(bd);
        LocationTwo two = new LocationTwo();
        two.setArguments(bd);
        mListFragment.add(one);
        mListFragment.add(two);
    }

    private void settingTabLayout() {
        mViewPager.setAdapter(new PlaceSingleViewPager(getSupportFragmentManager(),mListFragment,mListTitle));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void settingToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void givingId() {
        mToolbar = findViewById(R.id.place_single_toolbar);
        mTabLayout = findViewById(R.id.place_single_tab);
        mViewPager= findViewById(R.id.place_single_viewpager);
    }

    private class PlaceSingleViewPager extends FragmentPagerAdapter {
        private List<String> listone;
        private List<Fragment> listtwo;
        public PlaceSingleViewPager(FragmentManager fm, List<Fragment> mListFragment, List<String> mListTitle) {
            super(fm);
            listone=mListTitle;
            listtwo=mListFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return listtwo.get(position);
        }

        @Override
        public int getCount() {
            return listone.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return listone.get(position);
        }
    }

}
