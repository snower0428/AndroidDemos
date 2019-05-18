package com.lh.demos.widgets.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMainActivity extends BaseAppCompatActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);

        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
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
                Intent intent = new Intent(RecyclerViewMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                RecyclerViewMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Simple Recycler View", SimpleRecyclerViewActivity.class));
    }
}
