package com.yto.template.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class InfoPreviewActivity extends BaseActivity {

    private ViewPagerFixed viewPager;
    private LinearLayout ll_point;
    private FrameLayout fl_top;
    private LinearLayout ll_bottom;
    private ImageView back;
    private TextView tv_index;

    private int[] imageResIds; //存放图片资源id的数组
    private ArrayList<View> imageViews = null;

    private int lastPosition;
    private boolean infoShow = true;
    private Animation translateAnimation;

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
                tv_index.setText(position+1+"/3");
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
        tv_index = findViewById(R.id.tv_index);
        back = findViewById(R.id.tv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取fl_top的实际高度
//        int width = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1,
//                View.MeasureSpec.AT_MOST);
//        int height = View.MeasureSpec.makeMeasureSpec(Utils.dip2px(this,66),
//                View.MeasureSpec.EXACTLY);
//        int width = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1,
//                View.MeasureSpec.AT_MOST);
        int screenW = Utils.getScreenWidth(getApplicationContext());
        int screenH = Utils.getScreenHeight(getApplicationContext());
        System.out.println("屏幕宽度像素======="+ screenW+ "屏幕高度=="+ screenH);
//        int height = View.MeasureSpec.makeMeasureSpec(66,
//                View.MeasureSpec.EXACTLY);
//        int pixleH =Utils.dip2px(getApplicationContext(), 66);
//        System.out.println("pixel ========= "+ pixleH);
//        fl_top.measure(width, height);

        fl_top.measure(0, 0);
//        int measuredWidth = fl_top.getMeasuredWidth();// 获取宽度？79
//        int measuredHeight = fl_top.getMeasuredHeight();// 获取高度？83
//        System.out.println("FRAMELAYOUT的实际高度是===========:"+measuredHeight+" 宽度："+measuredWidth);//未知结果
        int fl_width = fl_top.getLayoutParams().width;//-1,建议直接获取屏幕宽
        final int fl_height = fl_top.getLayoutParams().height;//获取viewgroup的实际高度-99 pixel
        System.out.println("W="+fl_width+" H="+fl_height);

        ll_bottom.measure(0,0);
        final int ll_height = ll_bottom.getLayoutParams().height;
        System.out.println("ll_height= " + ll_height);//目前发现只有layout给固定值，这个才会有值

        final float scale = getResources().getDisplayMetrics().density;
        int value = (int) (100 * scale + 0.5f);
        System.out.println("scale="+scale+" value="+value);
        //方式一：加载资源配置方式
//        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.base_aniamtion_translate);
//        loadAnimation.setFillAfter(true);
//        fl_top.startAnimation(loadAnimation);
        //方式二：代码控制方式，推荐第二种
//        translateAnimation=new TranslateAnimation(0,0,0, -height);

        //初始化填充ViewPager的图片资源
        imageResIds = new int[]{R.mipmap.img_default,R.mipmap.img_default,R.mipmap.img_default};

        imageViews = new ArrayList<>();
        PhotoView imageView;
        View pointView;
        //循环遍历图片资源，然后保存到集合中
        for (int i = 0; i < imageResIds.length; i++){
            //添加图片到集合中
            imageView = new PhotoView(this);
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), imageResIds[i]));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if(infoShow){
                        slip(fl_top, 0, -fl_height);
                        slip(ll_bottom, 0, ll_height);
                        infoShow = false;
                        System.out.println("收起");
                    }else{
                        slip(fl_top, -fl_height,0);
                        slip(ll_bottom, ll_height, 0);
                        infoShow = true;
                        System.out.println("展开");
                    }
                }
            });

            imageViews.add(imageView);

            //加小白点，指示器（这里的小圆点定义在了drawable下的选择器中了，也可以用小图片代替）
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_selector); //使用选择器设置背景
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    Utils.dip2px(this,8),
                    Utils.dip2px(this,8));
            if (i != 0){
                //如果不是第一个点，则设置点的左边距
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false); //默认都是暗色的
            ll_point.addView(pointView, layoutParams);
        }
    }

    private void slip(View view, int fromY, int toY) {
        translateAnimation=new TranslateAnimation(0,0, fromY, toY);
        translateAnimation.setDuration(1000);//设置动画持续时间
        translateAnimation.setFillAfter(true);//移动之后保持状态移动后的状态
        view.setAnimation(translateAnimation);//设置动画效果
        view.startAnimation(translateAnimation);
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