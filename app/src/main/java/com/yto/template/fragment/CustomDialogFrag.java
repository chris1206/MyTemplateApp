package com.yto.template.fragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zc on 2018/3/21.
 */
@SuppressLint("ValidFragment")
public class CustomDialogFrag extends DialogFragment{
    @BindView(R.id.tv_dialog_title)
    TextView tv_dialog_title;
    @BindView(R.id.tv_dialog_msg)
    TextView tv_dialog_msg;
    @BindView(R.id.tv_dialog_cancle)
    TextView tv_dialog_cancle;
    @BindView(R.id.tv_dialog_ok)
    TextView tv_dialog_ok;
    private CharSequence title;
    private String titleColor;
    private boolean hasTitle;
    private CharSequence msg;
    private String msgColor;
    private CharSequence cancle;
    private String cancleColor;
    private boolean hasCancle;
    private CharSequence sure;
    private String sureColor;

    private OnDiaClickListen onDiaClickListen;

    public CustomDialogFrag(){

    }

    public CustomDialogFrag(Builder builder){
        this.title = builder.title;
        this.titleColor = builder.titleColor;
        this.hasTitle = builder.hasTitle;
        this.msg = builder.msg;
        this.msgColor = builder.msgColor;
        this.cancle = builder.cancle;
        this.cancleColor = builder.cancleColor;
        this.hasCancle = builder.hasCancle;
        this.sure = builder.sure;
        this.sureColor = builder.sureColor;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_normal,container);
        ButterKnife.bind(this, view);
        initView();
//        setRetainInstance(true);
        return view;
    }

    private void initView() {
        if(!hasTitle){
            tv_dialog_title.setVisibility(View.GONE);
        }else{
            tv_dialog_title.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(TextUtils.isEmpty(title)?"标题":title);
        }
        if(!hasCancle){
            tv_dialog_cancle.setVisibility(View.GONE);
        }else{
            tv_dialog_cancle.setVisibility(View.VISIBLE);
            tv_dialog_cancle.setText(TextUtils.isEmpty(cancle)?"按钮1":cancle);
        }
        tv_dialog_msg.setText(TextUtils.isEmpty(msg)?"内容描述，描述内容可以根据需要自定义或删除":msg);
        tv_dialog_ok.setText(TextUtils.isEmpty(sure)?"按钮2":sure);
    }

    @OnClick({R.id.tv_dialog_ok,R.id.tv_dialog_cancle})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_dialog_cancle:
//                if(onDiaClickListen != null) onDiaClickListen.setClick(this,false);
                ToastUtils.show("按钮1");
                dismiss();
                break;
            case R.id.tv_dialog_ok:
                if(onDiaClickListen != null) onDiaClickListen.setClick(this,true);
//                ToastUtils.show("按钮2");
//                dismiss();
                break;
        }
    }

    public static class Builder{
        private CharSequence title;
        private String titleColor;
        private boolean hasTitle = true;
        private CharSequence msg;
        private String msgColor;
        private CharSequence cancle;
        private String cancleColor;
        private boolean hasCancle = true;
        private CharSequence sure;
        private String sureColor ;

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setTitleColor(String titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setMsg(CharSequence msg) {
            this.msg = msg;
            return this;
        }

        public Builder setMsgColor(String msgColor) {
            this.msgColor = msgColor;
            return this;
        }

        public Builder setCancle(CharSequence cancle) {
            this.cancle = cancle;
            return this;
        }

        public Builder setCancleColor(String cancleColor) {
            this.cancleColor = cancleColor;
            return this;
        }

        public Builder setSure(CharSequence sure) {
            this.sure = sure;
            return this;
        }

        public Builder setSureColor(String sureColor) {
            this.sureColor = sureColor;
            return this;
        }

        public Builder setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        public Builder setHasCancle(boolean hasCancle) {
            this.hasCancle = hasCancle;
            return this;
        }

        public CustomDialogFrag create(){
            return new CustomDialogFrag(this);
        }
    }

    public void setListen(OnDiaClickListen onDiaClickListen){
        this.onDiaClickListen = onDiaClickListen;
    }

}
