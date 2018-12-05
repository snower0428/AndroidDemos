package com.lh.demos.animation;

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
import com.lh.demos.base.BaseBean;
import com.lh.demos.base.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leihui on 2018/10/22.
 * AnimationMainFragment
 */

public class AnimationMainFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_animation_main, container, false);
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
                getActivity().startActivity(intent);
            }
        });
    }

    private void initData() {
        mDataList.add(new BaseBean("TweenAnimation", TweenAnimationActivity.class));
        mDataList.add(new BaseBean("AnimationClickTest", AnimationClickTestActivity.class));
        mDataList.add(new BaseBean("OfObjectAnimation", OfObjectAnimationActivity.class));
    }
}
