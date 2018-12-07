package com.lh.demos.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lh.demos.R;

/**
 * Created by leihui on 2018/12/6.
 * SimpleRecyclerViewHolder
 */

public class SimpleRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public SimpleRecyclerViewHolder(View itemView) {
        super(itemView);

        mTextView = itemView.findViewById(R.id.tv_item);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public TextView getTextView() {
        return mTextView;
    }
}
