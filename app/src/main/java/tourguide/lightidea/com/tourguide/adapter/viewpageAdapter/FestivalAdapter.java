package tourguide.lightidea.com.tourguide.adapter.viewpageAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun on 5/19/2018.
 */

public class FestivalAdapter extends FragmentPagerAdapter {
    List<String> mListName = new ArrayList<>();
    List<Fragment> mListFragment = new ArrayList<>();
    public FestivalAdapter(FragmentManager fm, List<String> mListName, List<Fragment> mListFragment) {
        super(fm);
        this.mListName= mListName;
        this.mListFragment=mListFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListName.get(position);
    }
}
