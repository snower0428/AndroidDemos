package com.lh.demos.paints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lh.demos.R;
import com.lh.demos.paints.GraphicView;

/**
 * Created by Administrator on 2018/8/21.
 * GraphicFragment
 */

public class GraphicFragment extends Fragment {

    private FrameLayout mRoot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphic, container, false);
        mRoot = view.findViewById(R.id.root);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRoot.addView(new GraphicView(getActivity()));
    }
}
