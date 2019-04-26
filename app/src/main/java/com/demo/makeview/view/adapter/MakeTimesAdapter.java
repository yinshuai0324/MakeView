package com.demo.makeview.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.makeview.R;
import com.demo.makeview.view.bean.MakeBean;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author：JianFeng
 * @date：2019/3/26 16:36
 * @description：
 */
public class MakeTimesAdapter extends RecyclerView.Adapter<MakeTimesAdapter.ScrollViewHolder> {


    private Context context;
    private List<MakeBean.MakeItemBean> rightDatas;
    private OnItemClickListener listener;


    public MakeTimesAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MakeBean.MakeItemBean> rightDatas) {
        this.rightDatas = rightDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_make_item_time, viewGroup, false);
        return new ScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder scrollViewHolder, final int i) {
        final MakeBean.MakeItemBean bean = rightDatas.get(i);
        if (bean != null) {
            if (bean.isEnabled()) {
                scrollViewHolder.itemLayout.setBackgroundColor(Color.WHITE);
            } else {
                scrollViewHolder.itemLayout.setBackgroundColor(Color.GRAY);
            }

            if (bean.isOccupy()) {
                setItemVisibility(scrollViewHolder, VISIBLE);
                setData(scrollViewHolder, bean);
            } else {
                setItemVisibility(scrollViewHolder, GONE);
            }
        }

        scrollViewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(bean, MakeTimesAdapter.this, i);
                }
            }
        });

    }

    private void setData(ScrollViewHolder scrollViewHolder, MakeBean.MakeItemBean bean) {
        if (bean != null) {
            scrollViewHolder.remake.setText(bean.getRemark());
        }
    }


    public void setItemVisibility(ScrollViewHolder scrollViewHolder, int visibility) {
        scrollViewHolder.image.setVisibility(visibility);
        scrollViewHolder.tips.setVisibility(visibility);
        scrollViewHolder.remake.setVisibility(visibility);
    }

    @Override
    public int getItemCount() {
        return null == rightDatas ? 0 : rightDatas.size();
    }

    class ScrollViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemLayout;
        ImageView image;
        TextView tips;
        TextView remake;


        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.itemLayout);
            image = itemView.findViewById(R.id.image);
            tips = itemView.findViewById(R.id.tips);
            remake = itemView.findViewById(R.id.remake);
        }
    }


    public void setOnItemClickListener(OnItemClickListener l) {
        this.listener = l;
    }


    public interface OnItemClickListener {
        /**
         * 预约点击
         *
         * @param bean
         * @param adapter
         * @param position
         */
        void itemClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position);
    }

}
