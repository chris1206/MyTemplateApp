package com.yto.template.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionSheetActivity extends BaseActivity {


    private PopupWindow normalPop;
    private PopupWindow customPop;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_action_sheet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initNormalPop();
        initCustomPop();
    }
    @OnClick({R.id.tv_normal_sheet,R.id.tv_custom_sheet})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_normal_sheet:
                if(normalPop!=null&&!normalPop.isShowing()){
                    normalPop.showAtLocation(view.getRootView(), Gravity.BOTTOM,0,normalPop.getHeight());
                    darkenBackground(0.6f);
                }
                break;
            case R.id.tv_custom_sheet:
                if(customPop!=null&&!customPop.isShowing()){
                    customPop.showAtLocation(view.getRootView(), Gravity.BOTTOM,0,customPop.getHeight());
                    darkenBackground(0.6f);
                }
                break;
        }
    }

    private void initNormalPop() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.pop_normal, null);
        normalPop = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        normalPop.setFocusable(true);
        normalPop.setOutsideTouchable(true);
        TextView tv_pop_cancle = contentview.findViewById(R.id.tv_pop_cancle);
        tv_pop_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalPop.dismiss();
                darkenBackground(1f);
            }
        });

        normalPop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

    }
    private void initCustomPop() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.pop_custom, null);
        customPop = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        customPop.setFocusable(true);
        customPop.setOutsideTouchable(true);
        TextView tv_pop_cus_cancle = contentview.findViewById(R.id.tv_pop_cus_cancle);
        tv_pop_cus_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPop.dismiss();
                darkenBackground(1f);
            }
        });

        customPop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

    }
    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }
}
