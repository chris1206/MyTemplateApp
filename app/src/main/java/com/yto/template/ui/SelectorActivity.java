package com.yto.template.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.DateUtil;

import java.awt.font.NumericShaper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectorActivity extends BaseActivity {
    public static final String TAG = "SelectorActivity";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_selector_common)
    TextView tv_selector_common;
    @BindView(R.id.tv_selector_time)
    TextView tv_selector_time;
    @BindView(R.id.tv_selector_day)
    TextView tv_selector_day;
    @BindView(R.id.tv_selector_time_day)
    TextView tv_selector_time_day;
    @BindView(R.id.tv_selector_provincial)
    TextView tv_selector_provincial;
    @BindView(R.id.tv_selector_provincial_two)
    TextView tv_selector_provincial_two;


    private ArrayList<String> sItem = new ArrayList<>();
    private TimePickerView pvTime;
    private OptionsPickerView pvCustomOptions;
    private OptionsPickerView pvTimeOptions;
    List<Integer> hList;
    List<Integer> mList;
    List<String> tList;
    List<String> wList;
    PopupWindow pop;
    PopupWindow provincial_pop;
    private String[] items = {
            "川", "鄂", "甘", "赣", "桂", "贵", "黑", "沪", "吉", "冀", "津", "晋",
            "京", "辽", "鲁", "蒙", "闽", "宁", "青", "琼", "陕", "苏", "皖", "湘",
            "新", "渝", "豫", "粤", "云", "藏", "浙"
    };
    private String[] jItems = {
            "C",  "E",  "G",  "G",  "G",  "G",  "H",  "H",  "J",  "J",  "J",  "J",
            "J",  "L",  "L",  "M",  "M",  "N",  "Q",  "Q",  "S",  "S",  "W",  "X",
            "X",  "Y",  "Y",  "Y",  "Y",  "Z",  "Z"
    };
    private OptionsPickerView pvOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_selector;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("选择器");
        sItem.add("选项一");
        sItem.add("选项二");
        sItem.add("选项三");
        sItem.add("选项四");
        sItem.add("选项五");
        sItem.add("选项六");
        sItem.add("选项七");
        initCustomOptionPicker();

        initTimePicker();
        hList = new ArrayList();
        for(int i = 1;i<13;i++){
            hList.add(i);
        }
        mList = new ArrayList<>();
        for(int i =0;i<12;i++){
            mList.add(i*5);
        }
        tList = new ArrayList<>();
        tList.add("上午");
        tList.add("下午");
        initTime();

        wList = initDate();
        initTimeDayPicker();

        initProPop();
        initProTwoPop();
    }
    String w ;
    String t;
    String h;
    String m;



    @OnClick({R.id.back,R.id.tv_selector_common,R.id.tv_selector_time,R.id.tv_selector_day,R.id.tv_selector_time_day,R.id.tv_selector_provincial,R.id.tv_selector_provincial_two})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_selector_common:
                if(pvCustomOptions!=null){
                    pvCustomOptions.show();
                }
                break;
            case R.id.tv_selector_time:
                if(pvTimeOptions!=null){
                    pvTimeOptions.show();
                }
                break;
            case R.id.tv_selector_day:
                if(pvTime!=null){
                    pvTime.show();
                }
                break;
            case R.id.tv_selector_time_day:
                if(pop!=null&&!pop.isShowing()){
                    pop.showAtLocation(tv_selector_time_day.getRootView(),Gravity.BOTTOM,0,pop.getHeight());
                    darkenBackground(0.6f);
                }
                break;
            case R.id.tv_selector_provincial:
                if(provincial_pop!=null&&!provincial_pop.isShowing()){
                    provincial_pop.showAtLocation(tv_selector_provincial.getRootView(),Gravity.BOTTOM,0,provincial_pop.getHeight());
                    darkenBackground(0.6f);
                }
                break;
            case R.id.tv_selector_provincial_two:
                if(pvOptions!=null){
                    pvOptions.show();
                }
                break;
        }
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = sItem.get(options1);
                tv_selector_common.setText(tx);
            }
        })
                .setTitleText("请选择内容")
                .setCancelColor(Color.parseColor("#333333"))
                .setLineSpacingMultiplier(2.0f)

                .build();

        pvCustomOptions.setPicker(sItem);//添加数据

    }

    private void initTime() {
        pvTimeOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String hStr = String.format("%02d",hList.get(option2));
                String mStr = String.format("%02d",mList.get(options3));
                tv_selector_time.setText(tList.get(options1)+""+hStr+":"+mStr);
            }
        })
                .setCancelColor(Color.parseColor("#333333"))
                .setLineSpacingMultiplier(2.0f)
                .setTextXOffset(20,10,-20)
                .build();

        pvTimeOptions.setNPicker(tList,hList,mList);//添加数据
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013,0,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2050, 11, 31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                tv_selector_day.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setCancelColor(Color.parseColor("#333333"))
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private void initTimeDayPicker() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.pop_layout, null);
        pop = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);

        WheelView options1 = contentview.findViewById(R.id.options1);
        WheelAdapter wheelAdapter =  new ArrayWheelAdapter(wList);
        options1.setAdapter(wheelAdapter);
        options1.setCyclic(false);
        options1.setCurrentItem(0);


        WheelView options2 = contentview.findViewById(R.id.options2);
        WheelAdapter wheelAdapter2 =  new ArrayWheelAdapter(tList);
        options2.setAdapter(wheelAdapter2);
        options2.setCyclic(false);
        options2.setCurrentItem(0);

        WheelView options3 = contentview.findViewById(R.id.options3);
        WheelAdapter wheelAdapter3 =  new ArrayWheelAdapter(hList);
        options3.setAdapter(wheelAdapter3);
        options3.setCurrentItem(0);

        WheelView options4 = contentview.findViewById(R.id.options4);
        WheelAdapter wheelAdapter4 =  new ArrayWheelAdapter(mList);
        options4.setAdapter(wheelAdapter4);
        options4.setCurrentItem(0);

        Button btnCancel = contentview.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pop!=null&&pop.isShowing()){
                    pop.dismiss();
                    darkenBackground(1f);
                }
            }
        });
        Button btnSubmit = contentview.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hStr = String.format("%02d",hList.get(options3.getCurrentItem()));
                String mStr = String.format("%02d",mList.get(options4.getCurrentItem()));
                tv_selector_time_day.setText(wList.get(options1.getCurrentItem())+" "+tList.get(options2.getCurrentItem())+" "+hStr+":"+mStr);
                if(pop!=null&&pop.isShowing()){
                    pop.dismiss();
                    darkenBackground(1f);
                }
            }
        });
        pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);

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
    private ArrayList<String> initDate() {
        ArrayList<String> wList =new ArrayList<String>();
        Date date = new Date(System.currentTimeMillis());
        int year = DateUtil.getYear(date);
        int month = DateUtil.getMonth(date);
        int day = DateUtil.getDay(date);
        int maxDay = DateUtil.getCurrentMonthDay();
        for(int i =1 ; i<=maxDay ; i++){
            String s = year+"-"+month+"-"+i;
            long time = DateUtil.getMilliseconds(s);
            String week = DateUtil.DateToWeek(time);
            String data = month+"月"+i+"日  "+week;
            if(i == day){
                wList.add("今天");
            }else{
                wList.add(data);
            }
        }
        return wList;
    }

    public void initProPop(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.pop_pr, null);
        provincial_pop = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        provincial_pop.setFocusable(true);
        provincial_pop.setOutsideTouchable(true);
        provincial_pop.setBackgroundDrawable(new BitmapDrawable());

        provincial_pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(!provincial_pop.isShowing()){
                    darkenBackground(1f);
                }
            }
        });

        GridView gridview = contentview.findViewById(R.id.gridview);
        Adapter adapter = new ArrayAdapter<String>(this,R.layout.vehicle_gridview_item, Arrays.asList(items));
        gridview.setAdapter((ListAdapter) adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                tv_selector_provincial.setText(items[position]);
                if(provincial_pop!=null&&provincial_pop.isShowing()){
                    provincial_pop.dismiss();

                }
            }
        });

        provincial_pop.setAnimationStyle(R.style.MyPopupWindow_anim_style);
    }

    public void initProTwoPop(){
        List<String> jList = new ArrayList<>(Arrays.asList(jItems));
        List<String> pList = new ArrayList<>(Arrays.asList(items));

        List<String> list = new ArrayList<>();
        if(jList.size()==pList.size()){
            int size = jList.size();
            for (int i=0;i<size;i++){
                list.add(jList.get(i)+"    "+pList.get(i));
            }
            pvOptions = new OptionsPickerView.Builder(SelectorActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int i, int i1, int i2, View view) {
                    tv_selector_provincial_two.setText(items[i]);
                }
            }).build();
            pvOptions.setPicker(list);
        }
        jList.clear();
        pList.clear();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
