package com.lh.demos.bmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.demos.R;
import com.lh.demos.base.BaseConstants;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobAddDataActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ADD_RESULT_CODE = 0;
    public static final int ADD_REQUEST_CODE = 1;
    public static final String NAME_KEY = "Name";
    public static final String ADDRESS_KEY = "Address";
    public static final String OBJECT_ID_KEY = "ObjectId";

    private EditText mEditTextName;
    private EditText mEditTextAddress;
    private Button mBtnOk;

    private String mName;
    private String mAddress;
    private String mObjectId;

    private boolean mIsUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_add_data);

        Intent intent = getIntent();
        mName = intent.getStringExtra(NAME_KEY);
        mAddress = intent.getStringExtra(ADDRESS_KEY);
        mObjectId = intent.getStringExtra(OBJECT_ID_KEY);
        if (!TextUtils.isEmpty(mName) || !TextUtils.isEmpty(mAddress)) {
            mIsUpdate = true;
        }

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
        mEditTextName = findViewById(R.id.et_name);
        mEditTextAddress = findViewById(R.id.et_address);
        mBtnOk = findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);

        if (!TextUtils.isEmpty(mName)) {
            mEditTextName.setText(mName);
        }
        if (!TextUtils.isEmpty(mAddress)) {
            mEditTextAddress.setText(mAddress);
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btn_ok:
                onOk();
                break;
            default:
                break;
        }
    }

    private void onOk() {
        final String name = mEditTextName.getText().toString();
        final String address = mEditTextAddress.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address)) {
            PersonBean personBean = new PersonBean();
            personBean.setName(name);
            personBean.setAddress(address);
            if (mIsUpdate) {
                //更新数据
                personBean.update(mObjectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.d("lh123", "更新成功");
                            Intent data = new Intent();
                            data.putExtra(NAME_KEY, name);
                            data.putExtra(ADDRESS_KEY, address);
                            setResult(ADD_RESULT_CODE, data);
                            finish();
                        } else {
                            Log.d("lh123", "更新失败：" + e.getMessage());
                            Toast.makeText(BmobAddDataActivity.this, "更新失败！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                //添加数据
                personBean.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Log.d("lh123", "添加数据成功，返回objectId为：" + s);
                            Intent data = new Intent();
                            data.putExtra(NAME_KEY, name);
                            data.putExtra(ADDRESS_KEY, address);
                            setResult(ADD_RESULT_CODE, data);
                            finish();
                        } else {
                            Log.d("lh123", "创建数据失败：" + e.getMessage());
                            Toast.makeText(BmobAddDataActivity.this, "创建数据失败！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else {
            Toast.makeText(BmobAddDataActivity.this, "名字和地址不能为空！！！", Toast.LENGTH_SHORT).show();
        }
    }
}
