package com.lh.demos.bmob;

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
import com.lh.demos.bmob.data.BmobDemoActivity;
import com.lh.demos.bmob.push.BmobPushActivity;

import java.util.ArrayList;
import java.util.List;

public class BmobMainActivity extends BaseActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_main);
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
                Intent intent = new Intent(BmobMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                BmobMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Data", BmobDemoActivity.class));
        mDataList.add(new BaseBean("Push", BmobPushActivity.class));
    }
}
