package com.yto.template.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.yto.template.R;

import java.util.List;

/**
 * Created by Zc on 2018/1/29.
 */

public class MyAdapter extends RecyclerView.Adapter{
    private ItemClickListener mItemClickListener ;
    //数据源
    private List<String> dataList;
    private int type;
    Context context;
    //构造函数
    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }
    public MyAdapter(List<String> dataList,int type) {
        this.dataList = dataList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if(type == 0){
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_layout, null));
        }else if(type == 1){
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver_layout, parent,false));
        }else {
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BodyViewHolder) holder).getCheckBox().setText(dataList.get(position));
        int p = dataList.size() - 1;
        if(position == p){
            ((BodyViewHolder) holder).getCheckBox().setEnabled(false);
            ((BodyViewHolder) holder).getCheckBox().setBackgroundColor(Color.parseColor("#ffffff"));
            ((BodyViewHolder) holder).getCheckBox().setTextColor(Color.parseColor("#bbbbbb"));
            if(type == 1){
                Drawable drawable = context.getResources().getDrawable(R.drawable.oval_full_shape);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ((BodyViewHolder) holder).getCheckBox().setCompoundDrawables(null,null,drawable,null);
            }
        }

        ((BodyViewHolder) holder).getCheckBox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickClick((CheckBox) v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 给GridView中的条目用的ViewHolder，里面只有一个TextView
     */
    public class BodyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkbox;
        public BodyViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
        public CheckBox getCheckBox() {
            return checkbox;
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        public void onItemClickClick(CheckBox view,int position) ;
    }
}
