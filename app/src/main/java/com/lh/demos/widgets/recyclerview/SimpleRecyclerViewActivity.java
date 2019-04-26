package com.lh.demos.widgets.recyclerview;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.core.config.Global;
import com.lh.core.utils.ThreadUtil;
import com.lh.demos.R;
import com.lh.demos.base.BaseConstants;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerViewActivity extends AppCompatActivity implements SimpleRecyclerItemListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private SimpleRecyclerAdapter mAdapter;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_view);
        initToolbar();
        initView();
        loadData();
    }

    private void loadData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String strValue = String.valueOf(i+1);
            list.add(strValue);
        }
        mAdapter.setData(list);
    }

    private void loadMoreData() {
        ThreadUtil.executeMore(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = new ArrayList<>();
                        int index = mAdapter.getData().size();
                        for (int i = 0; i < 20; i++) {
                            String strValue = String.valueOf(index+i+1);
                            list.add(strValue);
                        }
                        mAdapter.setLoadingMore(false);
                        mAdapter.addData(list);
                    }
                }, 1000);
            }
        });
    }

    private void reloadData() {
        ThreadUtil.executeMore(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);

                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            String strValue = String.valueOf(i+1);
                            list.add(strValue);
                        }
                        mAdapter.setData(list);
                    }
                }, 1000);
            }
        });
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
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置Item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置Adapter
        mAdapter = new SimpleRecyclerAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        // 添加Header
        View headerView = LayoutInflater.from(this).inflate(R.layout.simple_recycler_header, mRecyclerView, false);
        mAdapter.addHeaderView(headerView);

        // 添加Footer
        View footerView = LayoutInflater.from(this).inflate(R.layout.simple_recycler_footer, mRecyclerView, false);
        mAdapter.addFooterView(footerView);

        // 添加Empty
        View emptyView = LayoutInflater.from(this).inflate(R.layout.simple_recycler_empty, mRecyclerView, false);
        mAdapter.setEmptyView(emptyView);

        // 添加LoadMore
        View loadMoreView = LayoutInflater.from(this).inflate(R.layout.simple_recycler_load_more, mRecyclerView, false);
        mAdapter.setLoadMoreView(loadMoreView);
        mAdapter.setISimpleRecycler(new ISimpleRecycler() {
            @Override
            public void startLoadMore() {
                loadMoreData();
            }
        });
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
