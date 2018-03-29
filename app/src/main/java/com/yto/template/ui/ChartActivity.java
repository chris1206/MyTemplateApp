package com.yto.template.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 2018/3/27.
 */

public class ChartActivity extends BaseActivity{
    private Toolbar mToolBar;
    private TextView tv_title;
    private List<String> mList = Arrays.asList("07-14", "15", "16","17","18","19","20");
    Random random;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_linechart;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initChart();
    }

    private int getRandom(int min, int max){
        int ret = 0;
        random = new Random();
        ret = random.nextInt(max)%(max-min+1) + min;
        return ret;
    }

    private void initChart() {
        LineChart mLineChart = (LineChart) findViewById(R.id.lineChart);
        //显示边界
        mLineChart.setDrawBorders(true);
        mLineChart.setBackgroundColor(getResources().getColor(R.color.white));
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
//            entries.add(new Entry(i, (int) (Math.random()) * 80));
            entries.add(new Entry(i, getRandom(8000, 10000)));
        }
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7, true);//设置X轴的刻度数量,true代表是否均分
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });

        YAxis rightYAxis = mLineChart.getAxisRight();
        rightYAxis.setEnabled(false); //右侧Y轴不显示

        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "周走势图");

        lineDataSet.setColor(getResources().getColor(R.color.indicator_color));
        lineDataSet.setCircleColor(getResources().getColor(R.color.indicator_color));
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        //线模式为圆滑曲线（默认折线）
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData data = new LineData(lineDataSet);
        mLineChart.setData(data);
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("折线图");
        mToolBar = findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}