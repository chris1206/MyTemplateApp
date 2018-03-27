package com.yto.template.ui;

import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Chris on 2018/3/15.
 *                  Camera API     Preview View
 * 4.0-4.4  14-20    Camera1        TextureView
 * >=5.0    >=21     Camera2        TextureView
 * 兼容方案：项目一般保证兼容4.0以上版本，所以需要适配两套相机拍摄方案
 */

public class PhotoActivity2 extends BaseActivity{
    private static final String TAG = PhotoActivity2.class.getSimpleName();

    int faceBackCameraId;
    int faceBackCameraOrientation;
    int faceFrontCameraId;
    int faceFrontCameraOrientation;

    public int currentCameraId;
    int numberOfCameras;
    //Camera
    Camera camera;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initCamera();

    }

    private void initCamera() {
        //有多少个摄像头
        numberOfCameras= Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCameras; ++i) {
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

            Camera.getCameraInfo(i, cameraInfo);
            //后置摄像头
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                faceBackCameraId = i;
                faceBackCameraOrientation = cameraInfo.orientation;
            }
            //前置摄像头
            else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                faceFrontCameraId = i;
                faceFrontCameraOrientation = cameraInfo.orientation;
            }
        }

        currentCameraId = faceBackCameraId;//默认打开后置摄像头

        //打开相机
        Camera camera = Camera.open(faceBackCameraId);
        Camera.Parameters parameters = camera.getParameters();
        String flashMode = parameters.getFlashMode();

        String focusMode = parameters.getFocusMode();

        String sceneMode = parameters.getSceneMode();

        //Camera1拍照
//        takePicture();
    }

    private void takePicture() {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                //存储返回的图像数据
                final File pictureFile = new File(Environment.getExternalStorageDirectory()+"/mycamera/");
                if (pictureFile == null) {
                    Log.d(TAG, "Error creating media file, check storage permissions.");
                    return;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(pictureFile);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                } catch (FileNotFoundException error) {
                    Log.e(TAG, "File not found: " + error.getMessage());
                } catch (IOException error) {
                    Log.e(TAG, "Error accessing file: " + error.getMessage());
                } catch (Throwable error) {
                    Log.e(TAG, "Error saving file: " + error.getMessage());
                }
            }
        });
    }

    //Camera 开启预览
    private void startPreview(SurfaceHolder surfaceHolder) {
        try {
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(currentCameraId, cameraInfo);
            int cameraRotationOffset = cameraInfo.orientation;

            //获取相机参数
            final Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);

            //设置对焦模式
//            setAutoFocus(camera, parameters);
//            //设置闪光模式
//            setFlashMode(mCameraConfigProvider.getFlashMode());
//
//            if (mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_PHOTO
//                    || mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_UNSPECIFIED)
//                turnPhotoCameraFeaturesOn(camera, parameters);
//            else if (mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_PHOTO)
//                turnVideoCameraFeaturesOn(camera, parameters);

            final int rotation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
            int degrees = 0;
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break; // Natural orientation
                case Surface.ROTATION_90:
                    degrees = 90;
                    break; // Landscape left
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;// Upside down
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;// Landscape right
            }

            //根据前置与后置摄像头的不同，设置预览方向，否则会发生预览图像倒过来的情况。
            int displayRotation;
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                displayRotation = (cameraRotationOffset + degrees) % 360;
                displayRotation = (360 - displayRotation) % 360; // compensate
            } else {
                displayRotation = (cameraRotationOffset - degrees + 360) % 360;
            }
            this.camera.setDisplayOrientation(displayRotation);

//            if (Build.VERSION.SDK_INT > 13
//                    && (mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_VIDEO
//                    || mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_UNSPECIFIED)) {
////                parameters.setRecordingHint(true);
//            }

//            if (Build.VERSION.SDK_INT > 14
//                    && parameters.isVideoStabilizationSupported()
//                    && (mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_VIDEO
//                    || mCameraConfigProvider.getMediaAction() == CameraConfig.MEDIA_ACTION_UNSPECIFIED)) {
//                parameters.setVideoStabilization(true);
//            }

            //设置预览大小
//            parameters.setPreviewSize(previewSize.getWidth(), previewSize.getHeight());
//            parameters.setPictureSize(photoSize.getWidth(), photoSize.getHeight());

            //设置相机参数
            camera.setParameters(parameters);
            //设置surfaceHolder
            camera.setPreviewDisplay(surfaceHolder);
            //开启预览
            camera.startPreview();

        } catch (IOException error) {
            Log.d(TAG, "Error setting camera preview: " + error.getMessage());
        } catch (Exception ignore) {
            Log.d(TAG, "Error starting camera preview: " + ignore.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stopPreview();
    }

    @Override
    protected void onStop() {
        super.onStop();
        camera.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=camera)
            camera.release();
    }
}
