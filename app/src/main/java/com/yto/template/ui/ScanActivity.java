package com.yto.template.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends BaseActivity  implements QRCodeView.Delegate {
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    @BindView(R.id.zxingview)
    ZXingView mQRCodeView;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_photo)
    TextView tv_photo;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkbox.setTextColor(Color.parseColor("#FFFF00"));
                    mQRCodeView.openFlashlight();
                }else{
                    checkbox.setTextColor(Color.parseColor("#FFFFFF"));
                    mQRCodeView.closeFlashlight();
                }
            }
        });
    }
    @OnClick(R.id.tv_photo)
    void onClick(View view){
//        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
//                .cameraFileDir(null)
//                .maxChooseCount(1)
//                .selectedPhotos(null)
//                .pauseOnScroll(false)
//                .build();
//        startActivityForResult(photoPickerIntent, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
        GalleryConfig config = new GalleryConfig.Build()
                .limitPickPhoto(1)
                .singlePhoto(false)
                .hintOfPick("this is pick hint")
                .filterMimeTypes(new String[]{})
                .build();
        GalleryActivity.openActivity(ScanActivity.this, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY, config);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this,"打开相机出错",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            List<String> picturePaths = (List<String>)data.getSerializableExtra(GalleryActivity.PHOTOS);
            String picturePath = picturePaths.get(0);

            MyAsyncTask myAsyncTask = new MyAsyncTask(picturePath);
            myAsyncTask.execute();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, String>{

        private String picturePath;

        public MyAsyncTask(String path){
            this.picturePath = path;
        }

        @Override
        protected String doInBackground(Void... voids) {
            return QRCodeDecoder.syncDecodeQRCode(picturePath);
        }

        @Override
        protected void onPostExecute(String result) {
            if (TextUtils.isEmpty(result)) {
                Toast.makeText(ScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ScanActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
