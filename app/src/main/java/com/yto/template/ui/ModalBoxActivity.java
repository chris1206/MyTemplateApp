package com.yto.template.ui;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.fragment.CustomDialogFrag;
import com.yto.template.fragment.CustomImgDialogFrag;
import com.yto.template.fragment.OnDiaClickListen;
import com.yto.template.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModalBoxActivity extends BaseActivity implements OnDiaClickListen{
    @BindView(R.id.title)
    TextView title;
    private CustomDialogFrag.Builder builder;
    private CustomDialogFrag customDialogFrag;
    private CustomDialogFrag.Builder builder2;
    private CustomDialogFrag customDialogFrag2;
    private CustomDialogFrag.Builder builder3;
    private CustomDialogFrag customDialogFrag3;
    private CustomImgDialogFrag.Builder imgBuilder;
    private CustomImgDialogFrag customImgDialogFrag;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_modal_box;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("模态框");
        builder = new CustomDialogFrag.Builder()
                .setHasCancle(false)
                .setSure("主要按钮");
        builder2 = new CustomDialogFrag.Builder();
        builder3 = new CustomDialogFrag.Builder()
                .setHasTitle(false);
        imgBuilder = new CustomImgDialogFrag.Builder();
        if(savedInstanceState!=null){
            customDialogFrag = (CustomDialogFrag) getFragmentManager().findFragmentByTag("CustomDialogFrag");
            if(customDialogFrag!=null){
                customDialogFrag.dismiss();
                customDialogFrag = builder.create();
                customDialogFrag.setListen(this);
                customDialogFrag.show(getFragmentManager(),"CustomDialogFrag");
            }

            customImgDialogFrag = (CustomImgDialogFrag) getFragmentManager().findFragmentByTag("CustomImgDialogFrag");
            if(customImgDialogFrag!=null){
                customImgDialogFrag.dismiss();
                customImgDialogFrag = imgBuilder.create();
                customImgDialogFrag.setListen(this);
                customImgDialogFrag.show(getFragmentManager(),"CustomImgDialogFrag");
            }

            customDialogFrag2 = (CustomDialogFrag) getFragmentManager().findFragmentByTag("CustomDialogFrag2");
            if(customDialogFrag2!=null){
                customDialogFrag2.dismiss();
                customDialogFrag2 = builder2.create();
                customDialogFrag2.setListen(this);
                customDialogFrag2.show(getFragmentManager(),"CustomDialogFrag");
            }

            customDialogFrag3 = (CustomDialogFrag) getFragmentManager().findFragmentByTag("CustomDialogFrag3");
            if(customDialogFrag3!=null){
                customDialogFrag3.dismiss();
                customDialogFrag3 = builder3.create();
                customDialogFrag3.setListen(this);
                customDialogFrag3.show(getFragmentManager(),"CustomDialogFrag3");
            }
        }






    }
    @OnClick({R.id.tv_nomal_dialog,R.id.tv_custom_dialog,R.id.tv_title_dialog,R.id.tv_not_title_dialog})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_nomal_dialog:
                customDialogFrag = builder.create();
                customDialogFrag.setListen(this);
                customDialogFrag.show(getFragmentManager(),"CustomDialogFrag");
                break;
            case R.id.tv_custom_dialog:
                customImgDialogFrag = imgBuilder.create();
                customImgDialogFrag.setListen(this);
                customImgDialogFrag.show(getFragmentManager(),"CustomImgDialogFrag");
                break;
            case R.id.tv_title_dialog:
                customDialogFrag2 = builder2.create();
                customDialogFrag2.setListen(this);
                customDialogFrag2.show(getFragmentManager(),"CustomDialogFrag2");
                break;
            case R.id.tv_not_title_dialog:
                customDialogFrag3 = builder3.create();
                customDialogFrag3.setListen(this);
                customDialogFrag3.show(getFragmentManager(),"CustomDialogFrag3");
                break;

        }
    }



    @Override
    public void setClick(DialogFragment fragment, boolean isSure) {
        if(fragment == customDialogFrag){
            if(isSure){
                ToastUtils.show("ok");
            }else{
                ToastUtils.show("cancle");
            }
            customDialogFrag.dismiss();
        } else if(fragment == customDialogFrag2){
            if(isSure){
                ToastUtils.show("按钮2");
            }else{
                ToastUtils.show("按钮1");
            }
            customDialogFrag2.dismiss();
        } else if(fragment == customDialogFrag3){
            if(isSure){
                ToastUtils.show("按钮2");
            }else{
                ToastUtils.show("按钮1");
            }
            customDialogFrag3.dismiss();
        } else if(fragment == customImgDialogFrag){
            if(isSure){
                ToastUtils.show("确定");
            }else{
                ToastUtils.show("关闭");
            }
            customImgDialogFrag.dismiss();
        }


    }
}
