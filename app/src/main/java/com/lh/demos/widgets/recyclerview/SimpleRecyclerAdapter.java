package com.lh.demos.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.demos.R;

import java.util.List;

/**
 * Created by leihui on 2018/12/6.
 * SimpleRecyclerAdapter
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerViewHolder> {

    private Context mContext;
    private List<String> mDataList;
    private SimpleRecyclerItemListener mItemListener;

    public SimpleRecyclerAdapter(Context context, List<String> dataList) {
        super();
        mContext = context;
        mDataList = dataList;
    }

    public void removeItem(int position) {
        if (position < mDataList.size()) {
            mDataList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(SimpleRecyclerItemListener listener) {
        mItemListener = listener;
    }

    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_recycler_item, parent, false);
        return new SimpleRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleRecyclerViewHolder holder, final int position) {
        String strText = mDataList.get(position);
        holder.setText(strText);
        if (mItemListener != null) {
            holder.getTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();
                    mItemListener.onItemClick(view, pos);
                }
            });
            holder.getTextView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getAdapterPosition();
                    mItemListener.onItemLongClick(view, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
