package tourguide.lightidea.com.tourguide.activity.Festival;

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
    private String data;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival);

        data = getIntent().getStringExtra("data");

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
        String string1,string2;

        if(data.equals("eng")){
            string1="Traditional";
            string2="Modern";
            title="Festival";
        }
        else if(data.equals("bur")){
            string1="ရိုးရာ";
            string2="မော်ဒန်ပွဲတော်";
            title="ပွဲတော်";
        }
        else{
            string1="传统民";
            string2="现代";
            title="节日";
        }

        getSupportActionBar().setTitle(title);
        mListName.add(string1);
        mListName.add(string2);
        Bundle bd   = new Bundle();
        bd.putString(getString(R.string.language),data);
        TraditionalOne traditionalOne = new TraditionalOne();
        traditionalOne.setArguments(bd);
        ModernTwo modernTwo = new ModernTwo();
        modernTwo.setArguments(bd);
        modernTwo.setArguments(bd);


        mListFragment.add(traditionalOne);
        mListFragment.add(modernTwo);

    }

    private void settingToolbar() {

        setSupportActionBar(mToolbar);
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
