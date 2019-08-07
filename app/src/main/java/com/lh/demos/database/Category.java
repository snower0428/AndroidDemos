package com.lh.demos.database;

import org.litepal.crud.LitePalSupport;

/**
 * Created by leihui on 2019/8/7.
 * Category
 */
public class Category extends LitePalSupport {

    private int id;
    private String categoryName;
    private int categoryCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
