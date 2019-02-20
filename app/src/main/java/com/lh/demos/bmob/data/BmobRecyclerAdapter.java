package com.lh.demos.bmob.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.core.config.Global;
import com.lh.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leihui on 2019/1/31.
 * BmobRecyclerAdapter
 */

public class BmobRecyclerAdapter extends RecyclerView.Adapter<BmobViewHolder> {

    private Context mContext;
    private BmobItemListener mListener;
    private List<PersonBean> mDataList = new ArrayList<>();
    private PersonBean mDeletePersonBean = null;

    public BmobRecyclerAdapter(Context context, BmobItemListener listener) {
        super();
        mContext = context;
        mListener = listener;
    }

    public void setData(List<PersonBean> list) {
        if (list == null) {
            return;
        }
        if (mDataList != null) {
            if (mDataList.size() > 0) {
                mDataList.clear();
            }
            mDataList.addAll(list);
        }
    }

    public List<PersonBean> getData() {
        return mDataList;
    }

    public void refreshData() {
        Global.runInMainThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public BmobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bmob_recycler_item, parent, false);
        return new BmobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BmobViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        final PersonBean personBean = mDataList.get(index);
        holder.setName(personBean.getName());
        holder.setAddress(personBean.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClickItem(index);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mListener != null) {
                    mListener.onLongClickItem(index);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
