package com.yto.template.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.Utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Chris on 2018/1/31.
 * 按钮界面
 */

public class ButtonActivity extends BaseActivity {
    private TextView title;
    private ImageView back;
    private GridView gridView;
    private SimpleAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_button;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("按钮");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        gridView = findViewById(R.id.gridview);
        initGridView();
    }

    private void initGridView() {
        ArrayList<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
        setGridViewDataSrc(lst);
        adapter = new SimpleAdapter(this, lst, R.layout.item_button_grid,
                new String[]{"itemImage", "itemText"},
                new int[]{R.id.iv, R.id.tv});
        gridView.setAdapter(adapter);
//        gridview.setOnItemClickListener(new GridViewOnClickListener());
    }

    private void setGridViewDataSrc(ArrayList<Map<String, Object>> lst) {

        lst.add(Utils.createMap("类型一", R.mipmap.gird_item1));
        lst.add(Utils.createMap("类型二", R.mipmap.gird_item2));
        lst.add(Utils.createMap("类型三", R.mipmap.gird_item3));
        lst.add(Utils.createMap("类型四", R.mipmap.gird_item4));
        lst.add(Utils.createMap("类型一", R.mipmap.gird_item1));
        lst.add(Utils.createMap("类型二", R.mipmap.gird_item2));
        lst.add(Utils.createMap("类型三", R.mipmap.gird_item3));
        lst.add(Utils.createMap("类型四", R.mipmap.gird_item4));
    }
}
