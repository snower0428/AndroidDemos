package com.lh.demos.base;

/**
 * Created by leihui on 2018/12/5.
 * BaseBean
 */

public class BaseBean {

    private String mItemTitle;
    private Class mItemClass;

    public BaseBean(String title, Class cls) {
        this.mItemTitle = title;
        this.mItemClass = cls;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        mItemTitle = itemTitle;
    }

    public Class getItemClass() {
        return mItemClass;
    }

    public void setItemClass(Class itemClass) {
        mItemClass = itemClass;
    }
}
