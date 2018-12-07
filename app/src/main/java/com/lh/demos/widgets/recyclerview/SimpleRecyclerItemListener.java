package com.lh.demos.widgets.recyclerview;

import android.view.View;

/**
 * Created by leihui on 2018/12/6.
 * SimpleRecyclerItemListener
 */

public interface SimpleRecyclerItemListener {

    // Item点击事件
    void onItemClick(View view, int position);

    // Item长按事件
    void onItemLongClick(View view, int position);
}
