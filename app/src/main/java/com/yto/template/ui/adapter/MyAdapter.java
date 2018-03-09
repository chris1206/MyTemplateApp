package com.yto.template.ui.adapter;

import android.graphics.Color;
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
        if(type == 0){
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_layout, null));
        }else{
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver_layout, parent,false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BodyViewHolder) holder).getCheckBox().setText(dataList.get(position));
        int p = dataList.size() - 1;
        if(position == p){
            ((BodyViewHolder) holder).getCheckBox().setEnabled(false);
            ((BodyViewHolder) holder).getCheckBox().setBackgroundColor(Color.parseColor("#cccccc"));
            if(type == 1){
                ((BodyViewHolder) holder).getCheckBox().setCompoundDrawables(null,null,null,null);
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
