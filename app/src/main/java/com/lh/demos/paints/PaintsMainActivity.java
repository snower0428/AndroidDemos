package com.lh.demos.paints;

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
import com.lh.demos.paints.xfermode.XfermodeMainActivity;

import java.util.ArrayList;
import java.util.List;

public class PaintsMainActivity extends BaseAppCompatActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints_main);

        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));
        initData();

        ListView listView = findViewById(R.id.list_view);
        SimpleListAdapter adapter = new SimpleListAdapter(this, mDataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaseBean bean = mDataList.get(i);
                Intent intent = new Intent(PaintsMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                PaintsMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Paint Graphic", GraphicActivity.class));
        mDataList.add(new BaseBean("Paint Path Text", PathTextActivity.class));
        mDataList.add(new BaseBean("Paint Region", RegionActivity.class));
        mDataList.add(new BaseBean("Canvas Operate", CanvasOperateActivity.class));
        mDataList.add(new BaseBean("Draw Text", DrawTextActivity.class));
        mDataList.add(new BaseBean("Bezier", BezierActivity.class));
        mDataList.add(new BaseBean("Xfermode", XfermodeMainActivity.class));
    }
}
