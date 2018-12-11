package com.lh.demos.widgets.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseConstants;

import java.util.ArrayList;
import java.util.List;

public class TabViewPagerActivity extends AppCompatActivity {

    private RecyclerTabLayout mRecyclerTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);
        initToolbar();
        initView();
    }

    private void initView() {
        List<String> dataList = new ArrayList<>();
        dataList.add("我的");
        dataList.add("推荐");
        dataList.add("夏日");
        dataList.add("活泼");
        dataList.add("舒缓");

        TabPagerAdapter adapter = new TabPagerAdapter();
        adapter.addAll(dataList);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        mRecyclerTabLayout = findViewById(R.id.recycler_tab_layout);
        mRecyclerTabLayout.setUpWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
    }
}
