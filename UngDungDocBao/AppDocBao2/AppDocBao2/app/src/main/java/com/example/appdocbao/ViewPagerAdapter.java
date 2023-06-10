package com.example.appdocbao;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TinTucFragMent();
            case 1:
                return new MpbileFragMent();
            case 2:
                return new GocNhinFragMent();
            case 3:
                return new TipFragMent();
            default:
                return new TinTucFragMent();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
