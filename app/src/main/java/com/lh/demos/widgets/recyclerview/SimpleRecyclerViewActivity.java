package com.lh.demos.widgets.recyclerview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lh.demos.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerViewActivity extends AppCompatActivity implements SimpleRecyclerItemListener {

    private RecyclerView mRecyclerView;
    private SimpleRecyclerAdapter mAdapter;
    private List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_view);

        initData();

        mRecyclerView = findViewById(R.id.recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置Item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置Adapter
        mAdapter = new SimpleRecyclerAdapter(this, mDataList);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            String strValue = String.valueOf(i+1);
            mDataList.add(strValue);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        String strText = "点击了第 " + position + " 条";
        Toast.makeText(SimpleRecyclerViewActivity.this, strText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, final int position) {
        new AlertDialog.Builder(SimpleRecyclerViewActivity.this)
                .setTitle("确定要删除吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("lh123", "取消");
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAdapter.removeItem(position);
                    }
                })
                .show();
    }
}
