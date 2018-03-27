package com.yto.template.fragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
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
public class CustomImgDialogFrag extends DialogFragment{
    @BindView(R.id.tv_dialog_title)
    TextView tv_dialog_title;
    @BindView(R.id.tv_dialog_msg)
    TextView tv_dialog_msg;
    @BindView(R.id.iv_dialog_cancle)
    ImageView iv_dialog_cancle;
    @BindView(R.id.tv_dialog_sure)
    TextView tv_dialog_sure;
    @BindView(R.id.iv_dialog_icon)
    ImageView iv_dialog_icon;

    private CharSequence title;
    private String titleColor;
    private boolean hasTitle;
    private CharSequence msg;
    private String msgColor;
    private CharSequence sure;
    private String sureColor;
    private int image;
    private Bitmap bitmap;

    private OnDiaClickListen onDiaClickListen;
    public CustomImgDialogFrag(){

    }

    public CustomImgDialogFrag(Builder builder){
        this.title = builder.title;
        this.titleColor = builder.titleColor;
        this.hasTitle = builder.hasTitle;
        this.msg = builder.msg;
        this.msgColor = builder.msgColor;
        this.sure = builder.sure;
        this.sureColor = builder.sureColor;
        this.image = builder.image;
        this.bitmap = builder.bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_image,container);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if(!hasTitle){
            tv_dialog_title.setVisibility(View.GONE);
        }else{
            tv_dialog_title.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(TextUtils.isEmpty(title)?"这是标题":title);
        }

        tv_dialog_msg.setText(TextUtils.isEmpty(msg)?"一般用作重要通知或操作引导，内容描述根据需求自定义":msg);
        tv_dialog_sure.setText(TextUtils.isEmpty(sure)?"确定":sure);
        if(image != 0 && bitmap == null){
            iv_dialog_icon.setImageResource(image);
        }
        if(bitmap!=null){
            iv_dialog_icon.setImageBitmap(bitmap);
        }

    }

    @OnClick({R.id.tv_dialog_sure,R.id.iv_dialog_cancle})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_dialog_cancle:
                if(onDiaClickListen != null) onDiaClickListen.setClick(this,false);
//                ToastUtils.show("关闭");
//                dismiss();
                break;
            case R.id.tv_dialog_sure:
                if(onDiaClickListen != null) onDiaClickListen.setClick(this,true);
//                ToastUtils.show("确定");
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
        private CharSequence sure;
        private String sureColor ;
        private int image;
        private Bitmap bitmap;

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


        public Builder setImage(@DrawableRes int image) {
            this.image = image;
            return this;
        }

        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public CustomImgDialogFrag create(){
            return new CustomImgDialogFrag(this);
        }
    }

    public void setListen(OnDiaClickListen onDiaClickListen){
        this.onDiaClickListen = onDiaClickListen;
    }

}
