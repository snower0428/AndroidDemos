package com.lh.demos.bmob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.demos.R;
import com.lh.demos.base.BaseActivity;
import com.lh.demos.base.BaseConstants;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobDemoActivity extends BaseActivity implements View.OnClickListener, BmobItemListener {

    private RecyclerView mRecyclerView;
    private BmobRecyclerAdapter mAdapter;
    private PersonBean mDeletePersonBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_demo);

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
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        Button btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(this);

        Button btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);

        Button btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new BmobRecyclerAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        queryData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == BmobAddDataActivity.ADD_RESULT_CODE) {
//            if (requestCode == BmobAddDataActivity.ADD_REQUEST_CODE) {
//                String name = data.getStringExtra(BmobAddDataActivity.NAME_KEY);
//                String address = data.getStringExtra(BmobAddDataActivity.ADDRESS_KEY);
//
//                Log.d("lh123", "Name:" + name + ",Address:" + address);
//            }
//        }
        queryData();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btn_add:
                onAdd();
                break;
            case R.id.btn_query:
                onQuery();
                break;
            case R.id.btn_update:
                onUpdate();
                break;
            case R.id.btn_delete:
                onDelete();
                break;
            default:
                break;
        }
    }

    private void onAdd() {
        Intent intent = new Intent(BmobDemoActivity.this, BmobAddDataActivity.class);
        startActivityForResult(intent, BmobAddDataActivity.ADD_REQUEST_CODE);
    }

    private void onQuery() {
        //查找PersonBean表里面id为676f1389ed的数据
        BmobQuery<PersonBean> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject("676f1389ed", new QueryListener<PersonBean>() {
            @Override
            public void done(PersonBean personBean, BmobException e) {
                if (e == null) {
                    Log.d("lh123", "查询成功：" + personBean.getName() + "," + personBean.getAddress());
                } else {
                    Log.d("lh123", "查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void onUpdate() {
        //更新PersonBean表里面id为676f1389ed的数据
        final PersonBean personBean = new PersonBean();
        personBean.setAddress("北京朝阳");
        personBean.update("676f1389ed", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("lh123", "更新成功：" + personBean.getUpdatedAt());
                } else {
                    Log.d("lh123", "更新失败：" + e.getMessage());
                }
            }
        });
    }

    private void onDelete() {
        final PersonBean personBean = new PersonBean();
        personBean.setObjectId("676f1389ed");
        personBean.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("lh123", "删除成功：" + personBean.getUpdatedAt());
                } else {
                    Log.d("lh123", "删除失败：" + e.getMessage());
                }
            }
        });
    }

    private void queryData() {
        BmobQuery<PersonBean> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<PersonBean>() {
            @Override
            public void done(List<PersonBean> list, BmobException e) {
                if (list != null && list.size() > 0) {
                    mAdapter.setData(list);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClickItem(int index) {
        List<PersonBean> list = mAdapter.getData();
        if (list != null && index < list.size()) {
            PersonBean personBean = list.get(index);
            Intent intent = new Intent(BmobDemoActivity.this, BmobAddDataActivity.class);
            intent.putExtra(BmobAddDataActivity.NAME_KEY, personBean.getName());
            intent.putExtra(BmobAddDataActivity.ADDRESS_KEY, personBean.getAddress());
            intent.putExtra(BmobAddDataActivity.OBJECT_ID_KEY, personBean.getObjectId());
            startActivityForResult(intent, BmobAddDataActivity.ADD_REQUEST_CODE);
        }
    }

    @Override
    public void onLongClickItem(int index) {
        List<PersonBean> list = mAdapter.getData();
        if (list != null && index < list.size()) {
            final PersonBean personBean = list.get(index);

            ViewHolder dialogHolder = new ViewHolder(R.layout.dialog_content);
            DialogPlus dialogPlus = DialogPlus.newDialog(BmobDemoActivity.this)
                    .setContentHolder(dialogHolder)
                    .setCancelable(true)
                    .setGravity(Gravity.BOTTOM)
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(DialogPlus dialog, View view) {
                            if (view instanceof TextView) {
                                String strText = ((TextView) view).getText().toString();
                                String strDelete = BmobDemoActivity.this.getResources().getString(R.string.delete);
                                String strCancel = BmobDemoActivity.this.getResources().getString(R.string.cancel);
                                if (strText.equals(strDelete)) {
                                    // 删除
                                    mDeletePersonBean = personBean;
                                    showAlertDialog();
                                } else if (strText.equals(strCancel)) {
                                    // 取消
                                    mDeletePersonBean = null;
                                }
                            }
                            dialog.dismiss();
                        }
                    })
                    .create();
            dialogPlus.show();
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(BmobDemoActivity.this);
        dialog.setTitle(R.string.delete);
        dialog.setMessage(R.string.delete_confirm);
        dialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 确定
                if (mDeletePersonBean != null) {
                    mDeletePersonBean.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Log.d("lh123", "删除成功");
                                queryData();
                            } else {
                                Log.d("lh123", "删除失败：" + e.getMessage());
                                Toast.makeText(BmobDemoActivity.this, "删除失败！！！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 取消
            }
        });
        dialog.show();
    }
}
