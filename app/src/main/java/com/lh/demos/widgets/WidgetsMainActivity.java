package com.lh.demos.widgets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lh.demos.R;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.SimpleListAdapter;
import com.lh.demos.widgets.cardview.CardViewActivity;
import com.lh.demos.widgets.marqueetext.MarqueeTextActivity;
import com.lh.demos.widgets.notification.NotificationMainActivity;
import com.lh.demos.widgets.rangeseekbar.RangeSeekBarActivity;
import com.lh.demos.widgets.recyclerview.RecyclerViewMainActivity;
import com.lh.demos.widgets.viewpager.TabViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

public class WidgetsMainActivity extends AppCompatActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos_main);
        initData();
        initView();
    }

    private void initView() {
        ListView listView = findViewById(R.id.list_view);

        SimpleListAdapter adapter = new SimpleListAdapter(this, mDataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaseBean bean = mDataList.get(i);
                Intent intent = new Intent(WidgetsMainActivity.this, bean.getItemClass());
                WidgetsMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("RecyclerView", RecyclerViewMainActivity.class));
        mDataList.add(new BaseBean("CardView", CardViewActivity.class));
        mDataList.add(new BaseBean("Notification", NotificationMainActivity.class));
        mDataList.add(new BaseBean("ViewPager", TabViewPagerActivity.class));
        mDataList.add(new BaseBean("Marquee Text", MarqueeTextActivity.class));
        mDataList.add(new BaseBean("Range Seek Bar", RangeSeekBarActivity.class));
    }
}
