package com.example.demo.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demo.Classes.Perit;
import com.example.demo.Fragments.EspecialitatsFragment;
import com.example.demo.Fragments.InfoFragment;
import com.example.demo.Fragments.PromoFragment;
import com.example.demo.Fragments.ZonesActuacioFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    /**
     * The number of tabs to show
     */
    private static final int NUM_TABS = 4;
    private Perit perit;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Perit perit) {
        super(fragmentActivity);
        this.perit = perit;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        // Return a NEW fragment instance in createFragment(int)
        switch (position) {
            case 0:
                fragment = new InfoFragment();
                break;
            case 1:
                fragment = new EspecialitatsFragment(perit);
                break;
            case 2:
                fragment = new ZonesActuacioFragment();
                break;
            case 3:
                fragment = new PromoFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }

}
