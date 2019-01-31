package com.lh.demos.bmob;

/**
 * Created by leihui on 2019/1/31.
 * BmobItemListener
 */

public interface BmobItemListener {

    /**
     * 点击
     * @param index 索引
     */
    void onClickItem(int index);

    /**
     * 长按
     * @param index 索引
     */
    void onLongClickItem(int index);
}
