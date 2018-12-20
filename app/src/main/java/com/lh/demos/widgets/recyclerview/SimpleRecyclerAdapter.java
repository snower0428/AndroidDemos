package com.lh.demos.widgets.recyclerview;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lh.core.config.Global;
import com.lh.demos.R;

import java.util.ArrayList;
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
    private static final int ITEM_TYPE_LOAD_MORE = 4;

    private Context mContext;
    private List<String> mDataList = new ArrayList<>();
    private SimpleRecyclerItemListener mItemListener;

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;
    private View mLoadMoreView;

    private ISimpleRecycler mISimpleRecycler;
    private boolean mIsLoadingMore = false;

    public SimpleRecyclerAdapter(Context context) {
        super();
        mContext = context;
    }

    public void addData(List<String> list) {
        if (list != null && list.size() > 0) {
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            }
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setData(List<String> list) {
        if (list != null && list.size() > 0) {
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            } else {
                mDataList.clear();
            }
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List<String> getData() {
        return mDataList;
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

    public void setLoadMoreView(View view) {
        mLoadMoreView = view;
        notifyDataSetChanged();
    }

    public void setISimpleRecycler(ISimpleRecycler simpleRecycler) {
        mISimpleRecycler = simpleRecycler;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
    }

    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new SimpleRecyclerViewHolder(mHeaderView);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new SimpleRecyclerViewHolder(mFooterView);
        } else if (viewType == ITEM_TYPE_EMPTY) {
            return new SimpleRecyclerViewHolder(mEmptyView);
        } else if (viewType == ITEM_TYPE_LOAD_MORE) {
            return new SimpleRecyclerViewHolder(mLoadMoreView);
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
                itemViewType == ITEM_TYPE_EMPTY ||
                itemViewType == ITEM_TYPE_LOAD_MORE) {
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
        if (mLoadMoreView != null && mDataList.size() > 10) {
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
        if (mLoadMoreView != null && position == getItemCount() - 2) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /*
                 * 在这里判段是否滑动到了最后一条数据
                 *
                 * LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager都提供了一个叫做
                 * public int findLastVisibleItemPositions()的方法
                 * 这个方法会返回当前可见的最后一条Item的 position
                 * 当 position == 我们的真实数据的长度-1时即表示滑到了最后
                 * 这时我们就可以触发上拉加载更多了
                 */
                int position = findLastVisibleItemPosition(recyclerView);
                if (position == getItemCount() - 1) {
                    if (mISimpleRecycler != null) {
                        if (!mIsLoadingMore) {
                            mISimpleRecycler.startLoadMore();
                        }
                        mIsLoadingMore = true;
                    }
                }
            }
        });
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    private int getRealItemPosition(int position) {
        if (mHeaderView != null) {
            return position - 1;
        }
        return position;
    }

    private int findLastVisibleItemPosition(RecyclerView recyclerView) {
        int position;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
            position = findMaxPosition(lastPositions);
        } else {
            position = recyclerView.getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    private int findMaxPosition(int[] positions) {
        int maxPosition = 0;
        for (int position : positions) {
            maxPosition = Math.max(maxPosition, position);
        }
        return maxPosition;
    }
}
