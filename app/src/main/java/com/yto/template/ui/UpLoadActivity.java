package com.yto.template.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.Content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpLoadActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.gv_take)
    GridView gv_take;
    @BindView(R.id.gv_take_photo)
    GridView gv_take_photo;

    GridAdapter takeAdapter;
    ArrayList<String> takeList;
    GridAdapter photoAdapter;
    ArrayList<String> photoList;
    private PopupWindow select_pop;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_load;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("图片上传");
        takeList = new ArrayList<String>();
        takeList.add("other");
        takeAdapter = new GridAdapter(takeList,0);
        gv_take.setAdapter(takeAdapter);

        photoList = new ArrayList<>();
        photoList.add("other");
        photoAdapter = new GridAdapter(photoList,1);
        gv_take_photo.setAdapter(photoAdapter);

        initProPop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 202&&requestCode == 201){
            String filePath = data.getStringExtra("filePath");
            int type = data.getIntExtra("type",0);
            if(type == 0){
                if(takeList.contains("other")){
                    takeList.remove("other");
                }
                takeList.add(filePath);
                takeList.add("other");
                takeAdapter = new GridAdapter(takeList,0);
                gv_take.setAdapter(takeAdapter);
            }else{
                if(photoList.contains("other")){
                    photoList.remove("other");
                }
                photoList.add(filePath);
                photoList.add("other");
                photoAdapter = new GridAdapter(photoList,1);
                gv_take_photo.setAdapter(photoAdapter);
            }

        }else if (resultCode == Activity.RESULT_OK && requestCode == Content.REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            List<String> picturePaths = (List<String>)data.getSerializableExtra(GalleryActivity.PHOTOS);
            if(photoList.contains("other")){
                photoList.remove("other");
            }
            photoList.addAll(picturePaths);
            photoList.add("other");
            photoAdapter = new GridAdapter(photoList,1);
            gv_take_photo.setAdapter(photoAdapter);
        }
    }

    public void initProPop(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.pop_normal, null);
        select_pop = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        select_pop.setFocusable(true);
        select_pop.setOutsideTouchable(true);
        select_pop.setBackgroundDrawable(new BitmapDrawable());

        select_pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(!select_pop.isShowing()){
                    darkenBackground(1f);
                }
            }
        });

        TextView tv_pop_cancle = contentview.findViewById(R.id.tv_pop_cancle);
        TextView tv_pop_one = contentview.findViewById(R.id.tv_pop_one);
        tv_pop_one.setVisibility(View.GONE);
        TextView tv_pop_three = contentview.findViewById(R.id.tv_pop_three);
        TextView tv_pop_two = contentview.findViewById(R.id.tv_pop_two);
        tv_pop_two.setText("拍摄");
        tv_pop_three.setText("从手机相册中选择");
        tv_pop_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select_pop!=null&&select_pop.isShowing()){
                    select_pop.dismiss();
                }
            }
        });

        tv_pop_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select_pop!=null&&select_pop.isShowing()){
                    select_pop.dismiss();
                }
                Intent intent = new Intent(UpLoadActivity.this,CameraActivity.class);
                intent.putExtra("type",1);
                startActivityForResult(intent,201);
            }
        });
        tv_pop_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select_pop!=null&&select_pop.isShowing()){
                    select_pop.dismiss();
                }
                int limit = 6;
                if(photoList.size()>1 && photoList.size()<7){
                    limit = limit - photoList.size()+1;
                }

                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(limit)
                        .singlePhoto(false)
                        .hintOfPick("选择达到上限")
                        .filterMimeTypes(new String[]{})
                        .build();
                GalleryActivity.openActivity(UpLoadActivity.this, Content.REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY, config);
            }
        });
        select_pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);
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
    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;
        private int type;
        public GridAdapter(ArrayList<String> listUrls,int type) {
            if(listUrls.size() >= 7){
                listUrls.subList(6,listUrls.size()).clear();
            }
            this.listUrls = listUrls;
            inflater = LayoutInflater.from(UpLoadActivity.this);
            this.type = type;
        }

        public int getCount(){
            return  listUrls.size();
        }
        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item, parent,false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                holder.iv_imag_cancle = (ImageView) convertView.findViewById(R.id.iv_imag_cancle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            final String path=listUrls.get(position);
            if (path.equals("other")){
                if(type==0){
                    holder.image.setImageResource(R.mipmap.camera_select);
                }else{
                    holder.image.setImageResource(R.mipmap.add);
                }
                holder.iv_imag_cancle.setVisibility(View.GONE);
            }else {
                Glide.with(UpLoadActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.defult_img)
                        .error(R.mipmap.defult_img)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
                holder.iv_imag_cancle.setVisibility(View.VISIBLE);
            }
            holder.iv_imag_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listUrls.remove(position);
                    if(!listUrls.contains("other")){
                        listUrls.add("other");
                    }
                    notifyDataSetChanged();
                }
            });

                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(path.equals("other")){
                            if(type == 0){
                                Intent intent = new Intent(UpLoadActivity.this,CameraActivity.class);
                                intent.putExtra("type",type);
                                startActivityForResult(intent,201);
                            }else{
                                select_pop.showAtLocation(gv_take_photo.getRootView(), Gravity.BOTTOM,0,select_pop.getHeight());
                                darkenBackground(0.6f);
                            }
                        }
                    }
                });


            return convertView;
        }
        class ViewHolder {
            ImageView image;
            ImageView iv_imag_cancle;
        }
    }
}
