package com.yto.template.customview.flowlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.R;

import java.util.List;

/**
 * Created by Zc on 2018/2/26.
 */

public class FlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context context;
    private int type = 0;
    private OnItemClickListen onItemClickListen;
    boolean isAll;
    public FlowAdapter(Context context,List<String> list) {
        this.list = list;
        this.context = context;
    }
    public FlowAdapter(Context context,List<String> list,int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }
    public FlowAdapter(Context context,List<String> list,int type,boolean isAll) {
        this.list = list;
        this.context = context;
        this.type = type;
        this.isAll = isAll;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(type == 1) return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_item,parent,false));
        else return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_item_one,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView textView = ((MyHolder) holder).text;
        textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
                onItemClickListen.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(type == 1 && !isAll) return 3;
        else return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.flow_text);
        }
    }

    public void setItemClickListen(OnItemClickListen onItemClickListen){
        this.onItemClickListen = onItemClickListen;
    }

    public interface OnItemClickListen{
        void onItemClick(View view,int position);
    }

}
