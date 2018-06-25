package tourguide.lightidea.com.tourguide.activity;

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
import tourguide.lightidea.com.tourguide.adapter.viewpageAdapter.FestivalAdapter;
import tourguide.lightidea.com.tourguide.fragment.s.festivalsFragment.ModernTwo;
import tourguide.lightidea.com.tourguide.fragment.s.festivalsFragment.TraditionalOne;

public class FestivalActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private List<String> mListName = new ArrayList<>();
    private List<Fragment> mListFragment = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival);

        givingId();

        settingToolbar();

        settingTabLayout();
    }

    private void settingTabLayout() {

        settingData();

        mViewPager.setAdapter(new FestivalAdapter(getSupportFragmentManager(),mListName,mListFragment));
        mViewPager.setOffscreenPageLimit(2);
        mTablayout.setupWithViewPager(mViewPager);
//        mTablayout.getTabAt(0).setIcon(R.drawable.tradition_icon);
//        mTablayout.getTabAt(1).setIcon(R.drawable.modern_icon);
    }

    private void settingData() {

        mListName.add("Traditional");
        mListName.add("Modern");

        mListFragment.add(new TraditionalOne());
        mListFragment.add(new ModernTwo());

    }

    private void settingToolbar() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Festival");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void givingId() {
        mTablayout = findViewById(R.id.festival_tablayout);
        mViewPager= findViewById(R.id.festival_viewpager);
        mToolbar = findViewById(R.id.festival_toolbar);
    }
}
