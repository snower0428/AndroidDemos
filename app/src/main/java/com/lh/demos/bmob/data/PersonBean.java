package com.lh.demos.bmob.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by leihui on 2019/1/30.
 * PersonBean
 */

public class PersonBean extends BmobObject {

    private String mName;
    private String mAddress;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
