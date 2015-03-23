package co.ikust.pomodorotimer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * <code>ViewPager</code> adapter for fragments.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    protected static final String EMPTY_TITLE = "";

    /**
     * List of fragments.
     */
    protected ArrayList<Fragment> fragments;

    /**
     * List of titles associated to fragments.
     */
    protected ArrayList<String> titles;

    /**
     *
     * @param fm
     */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
    }

    public void addItem(Fragment fragment) {
        fragments.add(fragment);
        titles.add(EMPTY_TITLE);
    }

    public void addItem(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
