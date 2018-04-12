package com.yto.template.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseV4Fragment;
import com.yto.template.module.bean.SimpleListBean;
import com.yto.template.ui.ButtonActivity;
import com.yto.template.ui.GridActivity;
import com.yto.template.ui.IconActivity;
import com.yto.template.ui.ListActivity;
import com.yto.template.ui.NavigationActivity;
import com.yto.template.ui.SegmentActivity;
import com.yto.template.ui.TabActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2018/1/26.
 * 常用组件封装
 */

public class FragBasicComponet extends BaseV4Fragment {

    private RecyclerView mRecyclerVIew;
    private MyAdapter myAdapter;
    private List<SimpleListBean> mList;
    private String[] titles = {"宫格", "导航栏", "分段控件", "标签栏", "按钮", "图标", "列表"};
    private String[] subtitles = {"Grid", "Navigation", "Segmented Controls", "Tab Bar", "Button", "Icon", "List"};
    @Override
    protected int getLayoutId() {
        return R.layout.frag_basic_componet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        myAdapter = new MyAdapter(mList);

        mRecyclerVIew =  rootView.findViewById(R.id.id_recyclerview);
        mRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));
        //注意属性修改在styles.xml AppTheme中定义,不设置，就是默认样式
        mRecyclerVIew.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerVIew.setAdapter(myAdapter);

    }

    private void initData() {
        mList = new ArrayList<>();
        SimpleListBean bean;
        for (int i=0; i<titles.length; i++){
            bean = new SimpleListBean();
            bean.setTitle(titles[i]);
            bean.setSub_title(subtitles[i]);
            mList.add(bean);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<SimpleListBean> mList;
        public MyAdapter(List<SimpleListBean> mList) {
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_simple_list, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(mList.get(position).getTitle());
            holder.subtitle.setText(mList.get(position).getSub_title());
            holder.rela_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0://跳转九宫格
                            startActivity(new Intent(mContext, GridActivity.class));
                            break;
                        case 1://跳转导航栏
                            startActivity(new Intent(mContext, NavigationActivity.class));
                            break;
                        case 2://跳转分段控件
                            startActivity(new Intent(mContext, SegmentActivity.class));
                            break;
                        case 3://跳转标签栏
                            startActivity(new Intent(mContext, TabActivity.class));
                            break;
                        case 4://跳转按钮
                            startActivity(new Intent(mContext, ButtonActivity.class));
                            break;
                        case 5://跳转图标
                            startActivity(new Intent(mContext, IconActivity.class));
                            break;
                        case 6://跳转列表
                            startActivity(new Intent(mContext, ListActivity.class));
                            break;
                        default:
                            break;
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView title;
            TextView subtitle;
            RelativeLayout rela_view;

            public MyViewHolder(View view)
            {
                super(view);
                title = (TextView) view.findViewById(R.id.tv_title);
                subtitle = (TextView) view.findViewById(R.id.tv_subtitle);
                rela_view = (RelativeLayout) view.findViewById(R.id.rela_view);
            }
        }
    }

}
