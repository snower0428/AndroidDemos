package com.lh.demos.paints;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.demos.R;

/**
 * Created by Administrator on 2018/8/30.
 * DrawTextFragment
 */

public class DrawTextFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw_text, container, false);
        return view;
    }
}
