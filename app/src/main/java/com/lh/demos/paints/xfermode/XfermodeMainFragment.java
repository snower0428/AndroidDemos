package com.lh.demos.paints.xfermode;

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
 * Created by leihui on 2018/10/12.
 * XfermodeMainFragment
 */

public class XfermodeMainFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_xfermode_main, container, false);
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
        mDataList.add(new BaseBean("AvoidXfermode", PorterDuffXfermodeActivity.class));
        mDataList.add(new BaseBean("AvoidXfermode2", PorterDuffXfermodeActivity2.class));
        mDataList.add(new BaseBean("LightBook", LightBookActivity.class));
        mDataList.add(new BaseBean("Twitter", TwitterActivity.class));
        mDataList.add(new BaseBean("RoundImageSrcIn", RoundImageMaskActivity.class));
        mDataList.add(new BaseBean("InvertedImageSrcIn", InvertedImageMaskActivity.class));
        mDataList.add(new BaseBean("DogViewSrcOut", DogViewSRCOUTActivity.class));
        mDataList.add(new BaseBean("GuaGuaCardSrcOut", GuaGuaCardSRCOUTActivity.class));
        mDataList.add(new BaseBean("CircleWaveDstIn", CircleWaveDSTINActivity.class));
        mDataList.add(new BaseBean("HeartMapDstIn", HeartMapActivity.class));
        mDataList.add(new BaseBean("IrregularWaveDstIn", IrregularWaveActivity.class));
        mDataList.add(new BaseBean("DstInTest", DstInDemoActivity.class));
    }
}
