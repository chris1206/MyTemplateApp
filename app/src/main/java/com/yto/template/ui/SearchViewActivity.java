package com.yto.template.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yto.template.customview.XEditText;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.flowlayout.FlowAdapter;
import com.yto.template.customview.flowlayout.FlowLayoutManager;
import com.yto.template.customview.flowlayout.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchViewActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.xet_search_scan)
    XEditText xet_search_scan;
    @BindView(R.id.xet_scan)
    XEditText xet_scan;

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
    @BindView(R.id.tv_all_history)
    TextView tv_all_history;

    private List<String> list = new ArrayList<>();
    private FlowAdapter flowAdapter;

    private List<String> tlist = new ArrayList<>();
    private FlowAdapter tflowAdapter;
    private boolean hasChecked = false;
    private PopupWindow historyPop;

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
        rv_one.setNestedScrollingEnabled(false);
        tlist.add("A20170102fff");
        tlist.add("A201701");
        tlist.add("A201701");
        tlist.add("A201701555");
        tlist.add("A201701666");
        tlist.add("A201701777");
        tflowAdapter = new FlowAdapter(this,tlist,1,hasChecked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_two.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_two.setLayoutManager(linearLayoutManager);
        rv_two.setAdapter(tflowAdapter);

        rv_two.setNestedScrollingEnabled(false);
        flowAdapter.setItemClickListen(new FlowAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                xet_histroy_one.setText(list.get(position));
                xet_histroy_one.setSelection(xet_histroy_one.length());
            }
        });

        tflowAdapter.setItemClickListen(new FlowAdapter.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                xet_histroy_two.setText(tlist.get(position));
                xet_histroy_two.setSelection(xet_histroy_two.length());
            }
        });
        xet_scan.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                startActivityForResult(new Intent(SearchViewActivity.this,ScanActivity.class).putExtra("from","searchR"),243);
            }
        });

        xet_histroy_one.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_SEARCH:
                        list.add(0,xet_histroy_one.getText().toString());
                        flowAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.back,R.id.tv_one_cancle,R.id.delete,R.id.tv_two_cancle,R.id.iv_scan,R.id.tv_all_history})
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
            case R.id.iv_scan:
                startActivityForResult(new Intent(SearchViewActivity.this,ScanActivity.class).putExtra("from","search"),241);
                break;
            case R.id.tv_all_history:
                if(!hasChecked){
                    hasChecked = true;
                    tflowAdapter.setAll(hasChecked);
                    tv_all_history.setText("收起");
                }else{
                    hasChecked = false;
                    tflowAdapter.setAll(hasChecked);
                    tv_all_history.setText("查看全部记录");
                }
                break;
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==241&&resultCode==242){
            String result = data.getStringExtra("result");
            xet_search_scan.setText(TextUtils.isEmpty(result)?"":result);
        }else if(requestCode==243&&resultCode==244){
            String result = data.getStringExtra("result");
            xet_scan.setText(TextUtils.isEmpty(result)?"":result);
        }
    }


}