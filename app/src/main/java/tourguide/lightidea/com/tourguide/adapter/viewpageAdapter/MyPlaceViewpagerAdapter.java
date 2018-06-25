package tourguide.lightidea.com.tourguide.adapter.viewpageAdapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.activity.PlaceActiviy;

/**
 * Created by Arun on 5/12/2018.
 */

public class MyPlaceViewpagerAdapter extends FragmentPagerAdapter {

    private List<String> mList = new ArrayList<>();
    private List<Fragment> mListFragment = new ArrayList<>();

    public MyPlaceViewpagerAdapter(Context context, FragmentManager fm,List<String> list,List<Fragment> listfrag) {
        super(fm);
        mList=list;
        mListFragment=listfrag;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
