package com.lh.demos.designs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.SimpleListAdapter;
import com.lh.demos.designs.command.CommandActivity;
import com.lh.demos.designs.decorator.DecoratorActivity;
import com.lh.demos.designs.observer.ObserverActivity;
import com.lh.demos.designs.strategy.StrategyActivity;

import java.util.ArrayList;
import java.util.List;

public class DesignsMainActivity extends BaseAppCompatActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs_main);

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
                Intent intent = new Intent(DesignsMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                DesignsMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Strategy", StrategyActivity.class));
        mDataList.add(new BaseBean("Observer", ObserverActivity.class));
        mDataList.add(new BaseBean("Decorator", DecoratorActivity.class));
        mDataList.add(new BaseBean("Command", CommandActivity.class));
    }
}
