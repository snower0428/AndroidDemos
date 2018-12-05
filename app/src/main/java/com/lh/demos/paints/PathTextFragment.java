package com.lh.demos.paints;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.lh.demos.R;
import com.lh.demos.paints.PathTextView;

/**
 * Created by Administrator on 2018/8/22.
 * PathTextFragment
 */

public class PathTextFragment extends Fragment {

    private ScrollView mScrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_path_text, container, false);
        mScrollView = view.findViewById(R.id.root);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PathTextView view = new PathTextView(getActivity());
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            view.setWidthHeight(width, height*2);
            mScrollView.addView(view);
        }
    }
}
