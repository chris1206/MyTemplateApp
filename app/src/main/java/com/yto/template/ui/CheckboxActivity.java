package com.yto.template.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.ui.adapter.MyAdapter;
import com.yto.template.ui.adapter.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckboxActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.recycle_view_vertical)
    RecyclerView recycle_view_vertical;

    List<CheckBox> vList;
    List<CheckBox> vList2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkbox;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        vList = new ArrayList<>();
        vList2 = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        dataList.add("选项一");
        dataList.add("选项二");
        dataList.add("选项三");
        dataList.add("选项四");
        dataList.add("选项五");
        dataList.add("禁选项");
        MyAdapter adapter = new MyAdapter(dataList);

        recyclerView.setAdapter(adapter);
        MyItemDecoration myItemDecoration = new MyItemDecoration();
        recyclerView.addItemDecoration(myItemDecoration);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setItemClickListener(new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClickClick(CheckBox view, int position) {
                if(view.isChecked()){
                    vList.add(view);
                    Toast.makeText(CheckboxActivity.this,view.getText(),Toast.LENGTH_SHORT).show();
                }else{
                    if(vList.contains(view)){
                        vList.remove(view);
                        Toast.makeText(CheckboxActivity.this,"移除"+view.getText(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        MyAdapter myAdapter = new MyAdapter(dataList,1);
        recycle_view_vertical.setAdapter(myAdapter);
        MyItemDecoration myItemDecoration1 = new MyItemDecoration(1);
        recycle_view_vertical.addItemDecoration(myItemDecoration1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle_view_vertical.setLayoutManager(linearLayoutManager);
        myAdapter.setItemClickListener(new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClickClick(CheckBox view, int position) {
                if(view.isChecked()){
                    vList2.add(view);
                    Toast.makeText(CheckboxActivity.this,"选中"+view.getText(),Toast.LENGTH_SHORT).show();
                }else{
                    if(vList2.contains(view)){
                        vList2.remove(view);
                        Toast.makeText(CheckboxActivity.this,"移除"+view.getText(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
