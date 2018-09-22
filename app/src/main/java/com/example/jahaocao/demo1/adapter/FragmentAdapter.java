package com.example.jahaocao.demo1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.man.title.TitleFragment;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List <Titles.NewsChannelList> mName;

    public FragmentAdapter(FragmentManager fm,  List<Titles.NewsChannelList> mName) {
        super(fm);
        this.mName = mName;
    }

    @Override
    public Fragment getItem(int position) {

        String hh=mName.get(position).getChannelId();
        String hh2=mName.get(position).getChannelName();
        TitleFragment titleFragment = new TitleFragment(hh,hh2);
        return titleFragment;
    }


    @Override
    public int getCount() {
        return mName.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mName.get(position).getChannelName();
}
}
