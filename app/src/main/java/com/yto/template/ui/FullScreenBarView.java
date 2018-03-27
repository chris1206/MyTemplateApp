package com.yto.template.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.QRCodeUtils;


/**
 * Created by Chris on 2017/7/24.
 */

public class FullScreenBarView extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        changeAppBrightness(255);
        ImageView iv_bar = findViewById(R.id.iv_bar);
        TextView tv_bar = findViewById(R.id.tv_bar);
        RelativeLayout rela_full = findViewById(R.id.rela_full);

        String cq_number = getIntent().getStringExtra("cq_number");

        try {
            iv_bar.setImageBitmap(QRCodeUtils.createOneDCode(cq_number));
        }catch (WriterException e) {
            e.printStackTrace();
        }

        tv_bar.setText(getInsertBlankString(cq_number));

        rela_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    public void changeAppBrightness(int brightness) {
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

    /**
     * 获取插入空格的字符串
     * @param number 车签号
     * @return 插入空格的字符串
     */
    public String getInsertBlankString(final String number) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<number.length(); i++) {
            sb.append(number.charAt(i)).append(" ");
        }
        return sb.toString();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fullscreen_bar;
    }


}
