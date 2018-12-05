package com.lh.demos.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lh.demos.R;

import java.util.List;

/**
 * Created by leihui on 2018/12/5.
 * SimpleListAdapter
 */

public class SimpleListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<BaseBean> mDataList;

    public SimpleListAdapter(Context context, List<BaseBean> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mLayoutInflater.inflate(R.layout.base_list_item, null);
        BaseBean bean = mDataList.get(i);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        tvTitle.setText(bean.getItemTitle());
        return v;
    }
}
