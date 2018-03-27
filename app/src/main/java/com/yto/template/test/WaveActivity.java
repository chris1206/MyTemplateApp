package com.yto.template.test;

import android.content.ContentValues;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CompoundButtonCompat;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.WaveHelper;
import com.yto.template.customview.WaveView;
import com.yto.template.ui.MainActivity;

/**
 * Created by Chris on 2018/1/16.
 */

public class WaveActivity extends BaseActivity {
    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wave;
    }

    //通过Handler传递数据
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ContentValues cv = (ContentValues) msg.obj;
//                    database = new Database(WaveActivity.this);
//                    dbRead = database.getReadableDatabase();
//                    cursorRead = dbRead.query("MyMessage", null,"body=? and date=?", new String[]{(String) cv.get("body"), (String) cv.get("date")}, null, null, null);
//                    if (cursorRead.getCount() == 0) {
//                        database = new Database(WaveActivity.this);      //这里是自己创建的一个数据库，把监听到的短信保存到这数据库
//                        dbWrite = database.getWritableDatabase();        //
//                        dbWrite.insert("MyMessage", null, cv);           //
//                        etNumber.setText((String) cv.get("address"));
//                        getContentResolver().delete(Uri.parse("content://sms/inbox"), "body=? and date=?",new String[]{(String) cv.get("body"), (String) cv.get("date")});//这里把那条数据给删除了，起到截获短信效果
//                        refreshListView();
//                    }
            }
        }
    };
    @Override
    protected void init(Bundle savedInstanceState) {
        final WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);
//        SMSContentObserver smsContentObserver = new SMSContentObserver(this, mHandler);
//        getContentResolver().registerContentObserver(Uri.parse("content://sms/inbox"), true, smsContentObserver);
        mWaveHelper = new WaveHelper(waveView);

        ((RadioGroup) findViewById(R.id.shapeChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.shapeCircle:
                                waveView.setShapeType(WaveView.ShapeType.CIRCLE);
                                break;
                            case R.id.shapeSquare:
                                waveView.setShapeType(WaveView.ShapeType.SQUARE);
                                break;
                            default:
                                break;
                        }
                    }
                });

        ((SeekBar) findViewById(R.id.seekBar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        mBorderWidth = i;
                        waveView.setBorder(mBorderWidth, mBorderColor);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorDefault),
                getResources().getColorStateList(android.R.color.white));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorRed),
                getResources().getColorStateList(R.color.red));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorGreen),
                getResources().getColorStateList(R.color.green));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) findViewById(R.id.colorBlue),
                getResources().getColorStateList(R.color.blue));

        ((RadioGroup) findViewById(R.id.colorChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.colorRed:
                                waveView.setWaveColor(
                                        Color.parseColor("#28f16d7a"),
                                        Color.parseColor("#3cf16d7a"));
                                mBorderColor = Color.parseColor("#44f16d7a");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorGreen:
                                waveView.setWaveColor(
                                        Color.parseColor("#40b7d28d"),
                                        Color.parseColor("#80b7d28d"));
                                mBorderColor = Color.parseColor("#B0b7d28d");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorBlue:
                                waveView.setWaveColor(
                                        Color.parseColor("#88b8f1ed"),
                                        Color.parseColor("#b8f1ed"));
                                mBorderColor = Color.parseColor("#b8f1ed");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            default:
                                waveView.setWaveColor(
                                        WaveView.DEFAULT_BEHIND_WAVE_COLOR,
                                        WaveView.DEFAULT_FRONT_WAVE_COLOR);
                                mBorderColor = Color.parseColor("#44FFFFFF");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }
}
