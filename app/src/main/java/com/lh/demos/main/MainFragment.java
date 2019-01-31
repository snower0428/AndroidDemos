package com.lh.demos.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lh.demos.R;
import com.lh.demos.animation.AnimationMainActivity;
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.base.SimpleListAdapter;
import com.lh.demos.bmob.BmobMainActivity;
import com.lh.demos.paints.PaintsMainActivity;
import com.lh.demos.widgets.WidgetsMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 * MainFragment
 */

public class MainFragment extends Fragment {

    private List<BaseBean> mDataList = new ArrayList<>();
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mListView = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SimpleListAdapter adapter = new SimpleListAdapter(getActivity(), mDataList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaseBean bean = mDataList.get(i);
                Intent intent = new Intent(getActivity(), bean.getItemClass());
                intent.putExtra(BaseConstants.NAVIGATION_TITLE_KEY, bean.getItemTitle());
                getActivity().startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("Widgets", WidgetsMainActivity.class));
        mDataList.add(new BaseBean("Animations", AnimationMainActivity.class));
        mDataList.add(new BaseBean("Paints", PaintsMainActivity.class));
        mDataList.add(new BaseBean("Bmob", BmobMainActivity.class));
    }
}
