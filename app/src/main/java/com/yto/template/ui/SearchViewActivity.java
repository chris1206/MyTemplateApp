package com.yto.template.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qbnet.viewutils.XEditText;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.flowlayout.FlowAdapter;
import com.yto.template.customview.flowlayout.FlowLayoutManager;
import com.yto.template.customview.flowlayout.SpaceItemDecoration;
import com.yto.template.ui.adapter.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchViewActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rv_one)
    RecyclerView rv_one;
    @BindView(R.id.xet_histroy_one)
    XEditText xet_histroy_one;
    @BindView(R.id.delete)
    ImageView delete;

    @BindView(R.id.rv_two)
    RecyclerView rv_two;
    @BindView(R.id.xet_histroy_two)
    XEditText xet_histroy_two;

    private List<String> list = new ArrayList<>();
    private FlowAdapter flowAdapter;

    private List<String> tlist = new ArrayList<>();
    private FlowAdapter tflowAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("搜索栏");
        list.add("A20170102fff");
        list.add("A201701");
        list.add("A201701");
        flowAdapter = new FlowAdapter(this,list);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        rv_one.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
        rv_one.setLayoutManager(flowLayoutManager);
        rv_one.setAdapter(flowAdapter);
        tlist.add("A20170102fff");
        tlist.add("A201701");
        tlist.add("A201701");
        tflowAdapter = new FlowAdapter(this,tlist,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_two.addItemDecoration(new MyItemDecoration(1));
        rv_two.setLayoutManager(linearLayoutManager);
        rv_two.setAdapter(tflowAdapter);

        flowAdapter.setItemClickListen(new FlowAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                xet_histroy_one.setText(list.get(position));
            }
        });

        tflowAdapter.setItemClickListen(new FlowAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                xet_histroy_two.setText(tlist.get(position));
            }
        });

    }

    @OnClick({R.id.back,R.id.tv_one_cancle,R.id.delete,R.id.tv_two_cancle})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.delete:
                list.clear();
                flowAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_one_cancle:
                xet_histroy_one.setText("");
                break;
            case R.id.tv_two_cancle:
                xet_histroy_two.setText("");
                break;
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}