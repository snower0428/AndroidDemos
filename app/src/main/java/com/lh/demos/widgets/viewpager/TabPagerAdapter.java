package com.lh.demos.widgets.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leihui on 2018/10/29.
 * TabPagerAdapter
 */

public class TabPagerAdapter extends PagerAdapter {

    private List<String> mDataList = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.tab_pager_layout, container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        return mDataList.get(position);
    }

    public String getDataItem(int position) {
        return mDataList.get(position);
    }

    public void addAll(List<String> list) {
        mDataList = new ArrayList<>(list);
    }
}
