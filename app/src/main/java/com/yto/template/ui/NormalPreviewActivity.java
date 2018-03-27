package com.yto.template.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.ViewPagerFixed;
import com.yto.template.utils.Utils;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Chris on 2018/3/26.
 */

public class NormalPreviewActivity extends BaseActivity{

    private ViewPagerFixed viewPager;
    private LinearLayout ll_point;
    private FrameLayout fl_top;
    private LinearLayout ll_bottom;

    private int[] imageResIds; //存放图片资源id的数组
    private ArrayList<View> imageViews = null;

    private int lastPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normal_preview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        initData();

        initAdapter();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当前的位置可能很大，为了防止下标越界，对要显示的图片的总数进行取余
                int newPosition = position % 3;
                //设置小圆点为高亮或暗色
                ll_point.getChildAt(lastPosition).setEnabled(false);
                ll_point.getChildAt(newPosition).setEnabled(true);
                lastPosition = newPosition; //记录之前的点
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        viewPager = findViewById(R.id.viewPager);
        ll_point = findViewById(R.id.ll_point);
        fl_top = findViewById(R.id.fl_top);
        ll_bottom = findViewById(R.id.ll_bottom);
        fl_top.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);

        //初始化填充ViewPager的图片资源
        imageResIds = new int[]{R.mipmap.img_default,R.mipmap.img_default,R.mipmap.img_default};

        imageViews = new ArrayList<>();
        PhotoView imageView;
        View pointView;
        //循环遍历图片资源，然后保存到集合中
        for (int i = 0; i < imageResIds.length; i++){
            //添加图片到集合中
            imageView = new PhotoView(this);
//            imageView.setBackgroundResource(imageResIds[i]);

            imageView.setImageBitmap(BitmapFactory.decodeResource(
                getResources(), imageResIds[i]));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
//        img.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                }
            });

            imageViews.add(imageView);

            //加小白点，指示器（这里的小圆点定义在了drawable下的选择器中了，也可以用小图片代替）
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_selector); //使用选择器设置背景
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
            if (i != 0){
                //如果不是第一个点，则设置点的左边距
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false); //默认都是暗色的
            ll_point.addView(pointView, layoutParams);
        }
    }

    /*
      初始化适配器
     */
    private void initAdapter() {
        ll_point.getChildAt(0).setEnabled(true); //初始化控件时，设置第一个小圆点为亮色
        lastPosition = 0; //设置之前的位置为第一个
        viewPager.setAdapter(new MyPagerAdapter(imageViews));
    }

    class MyPagerAdapter extends PagerAdapter {

        private ArrayList<View> listViews;

        private int size;

        public MyPagerAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public int getCount() {
            return size;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
        }

        public void finishUpdate(View arg0) {
        }

        public Object instantiateItem(View view, int position) {
            try {
                ((ViewPagerFixed) view).addView(listViews.get(position % size), 0);

            } catch (Exception e) {
            }
            return listViews.get(position % size);
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }


    }
}
