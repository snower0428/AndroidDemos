package com.lh.demos.paints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lh.demos.R;
import com.lh.demos.paints.BezierView;
import com.lh.demos.paints.BezierView2;

/**
 * Created by Administrator on 2018/8/30.
 * BezierFragment
 */

public class BezierFragment extends Fragment {

    private Button mBtnReset;
    private BezierView mBezierView;
    private BezierView2 mBezierView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bezier, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnReset = view.findViewById(R.id.btn_reset);
        mBezierView = view.findViewById(R.id.bezier_view);
        mBezierView2 = view.findViewById(R.id.bezier_view_2);

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBezierView.reset();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBezierView2.startAnim();
    }
}
