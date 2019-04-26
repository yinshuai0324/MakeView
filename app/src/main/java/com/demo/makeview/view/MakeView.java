package com.demo.makeview.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.demo.makeview.R;
import com.demo.makeview.view.adapter.MakeAdapter;
import com.demo.makeview.view.adapter.MakeTimesAdapter;
import com.demo.makeview.view.bean.MakeBean;

import java.util.List;

/**
 * 描述:自定义预约控件
 * 创建时间：2019/4/25-6:24 PM
 *
 * @author: yinshuai
 */
public class MakeView extends RelativeLayout implements MakeAdapter.OnStaffItemListener, MakeAdapter.OnMakeTimeItemListener, MakeAdapter.OnScrollChangeListener {
    private Context mContext;

    private MakeTimeBar timeBar;
    private RecyclerView recyclerView;
    private MakeAdapter adapter;


    private OnMakeClickListener onMakeClickListener;

    public MakeView(Context context) {
        super(context);
        mContext = context;
        initLayout();
    }

    public MakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initLayout();
    }

    public void initLayout() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_make, this);
        recyclerView = view.findViewById(R.id.recyclerView);
        timeBar = view.findViewById(R.id.timeBar);
        initRecyclerView();
    }


    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MakeAdapter(mContext);
        adapter.setOnMakeTimeItemListener(this);
        adapter.setOnStaffItemListener(this);
        recyclerView.setAdapter(adapter);
        //优化
        recyclerView.setItemViewCacheSize(30);
        adapter.setOnScrollChangeListener(this);
    }


    public void setTimeData(List<String> timeData) {
        if (timeData != null) {
            timeBar.setTimeData(timeData);
        }
    }


    public void setDataBean(List<MakeBean> beans) {
        if (beans == null) {
            return;
        }
        adapter.setDatas(beans);
    }


    /**
     * 员工休假点击
     *
     * @param bean
     */
    @Override
    public void staffItemClick(MakeBean bean) {
        if (onMakeClickListener != null) {
            onMakeClickListener.staffItemClick(bean);
        }
    }

    @Override
    public void timeClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position) {
        if (onMakeClickListener != null) {
            onMakeClickListener.timeClick(bean, adapter, position);
        }
    }


    public void setOnMakeClickListener(OnMakeClickListener l) {
        this.onMakeClickListener = l;
    }

    @Override
    public void change(int scrollX) {
        if (timeBar != null) {
            timeBar.scrollTo(scrollX, 0);
        }
    }


    public interface OnMakeClickListener {
        /**
         * 员工休假点击
         *
         * @param bean
         */
        void staffItemClick(MakeBean bean);

        /**
         * 预约点击
         *
         * @param bean
         * @param adapter
         * @param position
         */
        void timeClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position);
    }
}
