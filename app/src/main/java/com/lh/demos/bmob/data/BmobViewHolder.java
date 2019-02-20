package com.lh.demos.bmob.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lh.demos.R;

/**
 * Created by leihui on 2019/1/31.
 * BmobViewHolder
 */

public class BmobViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mAddress;

    public BmobViewHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.tv_name);
        mAddress = itemView.findViewById(R.id.tv_address);
    }

    public void setName(String name) {
        mName.setText(name);
    }

    public void setAddress(String address) {
        mAddress.setText(address);
    }
}
