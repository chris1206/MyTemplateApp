package com.yto.template.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PreviewActivity2 extends Activity {
    String filePath;
    private ImageView iv_shutter,id_iv_preview_photo,id_iv_cancel,id_iv_ok;
    private TextView tv_photo;
    private int imgPathsSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_preview);
        id_iv_preview_photo = findViewById(R.id.id_iv_preview_photo);

        id_iv_cancel = findViewById(R.id.id_iv_cancel);
        id_iv_ok = findViewById(R.id.id_iv_ok);
        iv_shutter = findViewById(R.id.iv_shutter);
        tv_photo = findViewById(R.id.tv_photo);
        iv_shutter.setVisibility(View.VISIBLE);
        tv_photo.setVisibility(View.VISIBLE);

        Intent intent = this.getIntent();
        if (intent != null) {
            //byte [] bis=intent.getByteArrayExtra("bitmapByte");

            filePath = intent.getStringExtra("filePath");
            // Toast.makeText(this, "图片加载filePath:"+filePath, Toast.LENGTH_SHORT).show();
            id_iv_preview_photo.setImageBitmap(getBitmapByUrl(filePath));
            imgPathsSize = intent.getIntExtra("imgSize",0);
            if(imgPathsSize >= 2){
                iv_shutter.setVisibility(View.GONE);
                tv_photo.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(this, "图片加载错误", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_iv_cancel:
                PreviewActivity2.this.finish();
                break;
            case R.id.id_iv_ok:
                Toast.makeText(this, "开始使用图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_shutter:
                Intent intent = new Intent();
                intent.putExtra("filePath", filePath);
                setResult(212,intent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 根据图片路径获取本地图片的Bitmap
     *
     * @param url
     * @return
     */
    public Bitmap getBitmapByUrl(String url) {
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try {
            fis = new FileInputStream(url);
            bitmap = BitmapFactory.decodeStream(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bitmap = null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fis = null;
            }
        }

        return bitmap;
    }
}
