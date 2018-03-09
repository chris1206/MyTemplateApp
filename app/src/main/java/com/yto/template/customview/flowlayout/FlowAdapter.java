package com.yto.template.customview.flowlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public FlowAdapter(Context context,List<String> list) {
        this.list = list;
        this.context = context;
    }
    public FlowAdapter(Context context,List<String> list,int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(type == 1){
            return new MyHolder(View.inflate(context, R.layout.flow_item, null));
        }else return new MyHolder(View.inflate(context, R.layout.flow_item_one, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView textView = ((MyHolder) holder).text;
        textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.flow_text);
        }
    }
}
