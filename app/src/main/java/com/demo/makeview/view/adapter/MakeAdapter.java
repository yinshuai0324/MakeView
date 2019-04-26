package com.demo.makeview.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.makeview.R;
import com.demo.makeview.view.MakeHorizontalScrollView;
import com.demo.makeview.view.bean.MakeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 创建时间：2019/4/26-4:29 PM
 *
 * @author: yinshuai
 */
public class MakeAdapter extends RecyclerView.Adapter<MakeAdapter.ItemViewHolder> {

    private Context mContext;


    private List<ItemViewHolder> mViewHolderList = new ArrayList<>();
    private int offestX = 0;

    private List<MakeBean> datas;
    private OnContentScrollListener onContentScrollListener;
    private OnStaffItemListener staffItemListener;
    private OnScrollChangeListener scrollChangeListener;
    private OnMakeTimeItemListener timeItemListener;

    public interface OnContentScrollListener {
        void onScroll(int offestX);
    }


    public MakeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_make_adapter, parent, false);
        return new ItemViewHolder(view);
    }


    public void setDatas(List<MakeBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final MakeBean bean = datas.get(position);
        holder.textName.setText(bean.getName());
        holder.textPost.setText(bean.getPost());

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setInitialPrefetchItemCount(11);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        MakeTimesAdapter adapter = new MakeTimesAdapter(mContext);
        holder.recyclerView.setAdapter(adapter);
        adapter.setDatas(bean.getTimes());

        adapter.setOnItemClickListener(new MakeTimesAdapter.OnItemClickListener() {
            @Override
            public void itemClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position) {
                if (timeItemListener != null) {
                    timeItemListener.timeClick(bean, adapter, position);
                }
            }
        });

        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffItemListener != null) {
                    staffItemListener.staffItemClick(bean);
                }
            }
        });

        //缓存当前holder
        if (!mViewHolderList.contains(holder)) {
            mViewHolderList.add(holder);
        }

        //滑动单个ytem的rv时,右侧整个区域的联动
        holder.scrollView.setOnCustomScrollChangeListener(new MakeHorizontalScrollView.OnMakeScrollChangeListener() {
            @Override
            public void onCustomScrollChange(MakeHorizontalScrollView listener, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollChangeListener != null) {
                    scrollChangeListener.change(scrollX);
                }
                for (int i = 0; i < mViewHolderList.size(); i++) {
                    ItemViewHolder touchViewHolder = mViewHolderList.get(i);
                    if (touchViewHolder != holder) {
                        touchViewHolder.scrollView.scrollTo(scrollX, 0);
                    }
                }
                //记录滑动距离,便于处理下拉刷新之后的还原操作
                if (null != onContentScrollListener) {
                    onContentScrollListener.onScroll(scrollX);
                }
                offestX = scrollX;
            }
        });
        //由于viewHolder的缓存,在1级缓存取出来是2个viewholder,并且不会被重新赋值,所以这里需要处理缓存的viewholder的位移
        holder.scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!holder.isLayoutFinish()) {
                    holder.scrollView.scrollTo(offestX, 0);
                    holder.setLayoutFinish(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    public List<ItemViewHolder> getViewHolderCacheList() {
        return mViewHolderList;
    }

    public int getOffestX() {
        return offestX;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMore;
        ImageView imageHead;
        TextView textName;
        TextView textPost;
        MakeHorizontalScrollView scrollView;
        RecyclerView recyclerView;
        /**
         * //自定义字段,用于标记layout
         */
        private boolean isLayoutFinish;

        public boolean isLayoutFinish() {
            return isLayoutFinish;
        }

        public void setLayoutFinish(boolean layoutFinish) {
            isLayoutFinish = layoutFinish;
        }

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMore = itemView.findViewById(R.id.imageMore);
            imageHead = itemView.findViewById(R.id.imageHead);
            textName = itemView.findViewById(R.id.textName);
            textPost = itemView.findViewById(R.id.textPost);
            scrollView = itemView.findViewById(R.id.scrollView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }


    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        this.scrollChangeListener = l;
    }


    public void setOnStaffItemListener(OnStaffItemListener l) {
        this.staffItemListener = l;
    }


    public void setOnMakeTimeItemListener(OnMakeTimeItemListener l) {
        this.timeItemListener = l;
    }

    public interface OnMakeTimeItemListener {
        /**
         * 预约点击监听
         *
         * @param bean
         * @param adapter
         * @param position
         */
        void timeClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position);
    }

    public interface OnStaffItemListener {
        /**
         * 员工休假点击
         *
         * @param bean
         */
        void staffItemClick(MakeBean bean);
    }


    public interface OnScrollChangeListener {
        /**
         * item滑动监听
         *
         * @param scrollX
         */
        void change(int scrollX);
    }
}
