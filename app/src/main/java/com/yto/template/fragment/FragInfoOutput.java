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
import com.yto.template.ui.CardActivity;
import com.yto.template.ui.GridActivity;
import com.yto.template.ui.IconActivity;
import com.yto.template.ui.ImagePreviewActivity;
import com.yto.template.ui.ListActivity;
import com.yto.template.ui.NavigationActivity;
import com.yto.template.ui.QRCodeActivity;
import com.yto.template.ui.SegmentActivity;
import com.yto.template.ui.TabActivity;
import com.yto.template.ui.TimeLineActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2018/1/26.
 */

public class FragInfoOutput extends BaseV4Fragment {
    private RecyclerView mRecyclerVIew;
    private MyAdapter myAdapter;
    private TextView title;
    private List<SimpleListBean> mList;
    private String[] titles = {"卡片", "时间轴", "条形码", "图片预览", "图表"};
    private String[] subtitles = {"Card", "Time Line", "Bar Code", "Segmented Controls", "Table"};
    @Override
    protected int getLayoutId() {
        return R.layout.frag_basic_componet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
        myAdapter = new MyAdapter(mList);
        title = rootView.findViewById(R.id.title);
        title.setText("信息输出/Data Display");
        mRecyclerVIew =  rootView.findViewById(R.id.id_recyclerview);
        mRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));
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
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyAdapter.MyViewHolder holder = new MyAdapter.MyViewHolder(inflater.inflate(R.layout.item_simple_list, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.title.setText(mList.get(position).getTitle());
            holder.subtitle.setText(mList.get(position).getSub_title());
            holder.rela_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0://跳转卡片
                            startActivity(new Intent(mContext, CardActivity.class));
                            break;
                        case 1://跳转时间轴
                            startActivity(new Intent(mContext, TimeLineActivity.class));
                            break;
                        case 2://跳转条形码
                            startActivity(new Intent(mContext, QRCodeActivity.class));
                            break;
                        case 3://跳转图片预览
                            startActivity(new Intent(mContext, ImagePreviewActivity.class));
                            break;
//                        case 4://跳转图表
//                            startActivity(new Intent(mContext, ButtonActivity.class));
//                            break;
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
