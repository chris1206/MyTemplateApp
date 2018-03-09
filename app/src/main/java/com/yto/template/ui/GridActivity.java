package com.yto.template.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.DividerGridItemDecoration;

/**
 * Created by Chris on 2018/1/29.
 */

public class GridActivity extends BaseActivity {
    private Toolbar mToolBar;
    private TextView tv_title;
    private LinearLayout view_container;
    private RecyclerView mRecyclerVIew;
    private RecyclerGridViewAdapter myAdapter;
    private String[] titles = {"我的运单", "异常上报", "消息", "我的", "设置"};
    private int[] imgs = {R.drawable.mywaybill_selector, R.drawable.exception_report_selector,
            R.drawable.msg_selector, R.drawable.mine_selector, R.drawable.setting_selector};

    @Override
    protected int getLayoutId() {
        return R.layout.frag_grid;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initData();

        initView();

    }

    private void initData() {

        myAdapter = new RecyclerGridViewAdapter(this, titles, imgs);
        myAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Snackbar.make(view,"你点了"+position,Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("九宫格");
        mToolBar = findViewById(R.id.toolbar);
        view_container = findViewById(R.id.frag_grid_container);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerVIew = findViewById(R.id.id_recyclerview);
        mRecyclerVIew.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerVIew.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerVIew.setAdapter(myAdapter);

    }

}

class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.MyViewHolder> {

    private Context mContext;
    private String[] data;
    private int[] imgdata;
    private LayoutInflater inf;

    public interface OnRecyclerViewItemListener {
        void onItemClickListener(View view, int position);
        void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public RecyclerGridViewAdapter(Context mContext, String[] data, int[] imgdata) {
        this.mContext = mContext;
        this.data = data;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerGridViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerGridViewAdapter.MyViewHolder holder = new RecyclerGridViewAdapter.MyViewHolder(inf.inflate(R.layout.item_grid, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerGridViewAdapter.MyViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        if (mOnRecyclerViewItemListener != null) {
            itemOnClick(holder);
            itemOnLongClick(holder);
        }
        holder.title.setText(data[position]);
        holder.iv.setImageDrawable(mContext.getResources().getDrawable(imgdata[position]));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    //单击事件
    private void itemOnClick(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnRecyclerViewItemListener.onItemClickListener(holder.itemView, position);
            }
        });
    }
    //长按事件
    private void itemOnLongClick(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getLayoutPosition();
                mOnRecyclerViewItemListener.onItemLongClickListener(holder.itemView, position);
                //返回true是为了防止触发onClick事件
                return true;
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        ImageView iv;

        public MyViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            iv = (ImageView) view.findViewById(R.id.iv_desc);
        }
    }
}
