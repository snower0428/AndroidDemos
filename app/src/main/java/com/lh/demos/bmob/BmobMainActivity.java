package com.lh.demos.bmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.SimpleListAdapter;
import com.lh.demos.bmob.data.BmobDemoActivity;
import com.lh.demos.bmob.push.BmobPushActivity;

import java.util.ArrayList;
import java.util.List;

public class BmobMainActivity extends BaseAppCompatActivity {

    private List<BaseBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_main);

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
                Intent intent = new Intent(BmobMainActivity.this, bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                BmobMainActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Data", BmobDemoActivity.class));
        mDataList.add(new BaseBean("Push", BmobPushActivity.class));

//        List<Integer> list = new ArrayList<>();
//        int array[] = {4, 5, 8, 3, 1, 2, 10, 29, 6};
//        for (int i = 0; i < array.length; i++) {
//            list.add(array[i]);
//        }
//        Log.d("lh123", "Before:" + list);
//        List<Integer> resultList = quickSort(list);
//        Log.d("lh123", "After:" + resultList);
    }

    private List<Integer> quickSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        } else {
            Integer pivot = list.get(0);
            List<Integer> less = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();
            for (Integer item : list) {
                if (item < pivot) {
                    less.add(item);
                } else if (item > pivot) {
                    greater.add(item);
                }
            }
            List<Integer> result = new ArrayList<>();
            result.addAll(quickSort(less));
            result.add(pivot);
            result.addAll(quickSort(greater));
            return result;
        }
    }
}
