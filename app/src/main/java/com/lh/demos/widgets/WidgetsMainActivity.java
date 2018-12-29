package com.lh.demos.widgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseActivity;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.SimpleListAdapter;
import com.lh.demos.widgets.audiocut.AudioCutActivity;
import com.lh.demos.widgets.cardview.CardViewActivity;
import com.lh.demos.widgets.marqueetext.MarqueeTextActivity;
import com.lh.demos.widgets.notification.NotificationMainActivity;
import com.lh.demos.widgets.rangeseekbar.RangeSeekBarActivity;
import com.lh.demos.widgets.recyclerview.RecyclerViewMainActivity;
import com.lh.demos.widgets.viewpager.TabViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

public class WidgetsMainActivity extends BaseActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos_main);
        initData();
        initToolbar();
        initView();
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

    private void initView() {
        ListView listView = findViewById(R.id.list_view);
        SimpleListAdapter adapter = new SimpleListAdapter(this, mDataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaseBean bean = mDataList.get(i);
                Intent intent = new Intent(WidgetsMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
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
        mDataList.add(new BaseBean("AudioCut", AudioCutActivity.class));
    }
}
