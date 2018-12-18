package com.lh.demos.widgets.recyclerview;

import android.content.Context;
import android.content.IntentFilter;
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

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;
    private static final int ITEM_TYPE_EMPTY = 3;

    private Context mContext;
    private List<String> mDataList;
    private SimpleRecyclerItemListener mItemListener;

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;

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

    public void addHeaderView(View view) {
        mHeaderView = view;
        notifyItemInserted(0);
    }

    public void addFooterView(View view) {
        mFooterView = view;
        notifyItemChanged(getItemCount() - 1);
    }

    public void setEmptyView(View view) {
        mEmptyView = view;
        notifyDataSetChanged();
    }

    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new SimpleRecyclerViewHolder(mHeaderView);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new SimpleRecyclerViewHolder(mFooterView);
        } else if (viewType == ITEM_TYPE_EMPTY) {
            return new SimpleRecyclerViewHolder(mEmptyView);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.simple_recycler_item, parent, false);
            return new SimpleRecyclerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final SimpleRecyclerViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_HEADER ||
                itemViewType == ITEM_TYPE_FOOTER ||
                itemViewType == ITEM_TYPE_EMPTY) {
            return;
        }
        int realItemPosition = getRealItemPosition(position);
        String strText = mDataList.get(realItemPosition);
        holder.setText(strText);
        if (mItemListener != null) {
            holder.getTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();
                    int realItemPosition = getRealItemPosition(pos);
                    mItemListener.onItemClick(view, realItemPosition);
                }
            });
            holder.getTextView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getAdapterPosition();
                    int realItemPosition = getRealItemPosition(pos);
                    mItemListener.onItemLongClick(view, realItemPosition);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mDataList.size();
        if (mEmptyView != null && mDataList.size() == 0) {
            itemCount++;
        }
        if (mHeaderView != null) {
            itemCount++;
        }
        if (mFooterView != null) {
            itemCount++;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return ITEM_TYPE_HEADER;
        }
        if (mFooterView != null && position == getItemCount() - 1) {
            return ITEM_TYPE_FOOTER;
        }
        if (mEmptyView != null && mDataList.size() == 0) {
            return ITEM_TYPE_EMPTY;
        }
        return ITEM_TYPE_NORMAL;
    }

    private int getRealItemPosition(int position) {
        if (mHeaderView != null) {
            return position - 1;
        }
        return position;
    }
}
