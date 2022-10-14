package com.martin.ads.pano360demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.martin.ads.pano360demo.databinding.ActivityCustomBinding;
import com.martin.ads.vrlib.PanoViewWrapper;
import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;

import java.io.File;

public class CustomActivity extends AppCompatActivity {

    private ActivityCustomBinding mBinding;
    @Nullable private PanoViewWrapper mPanoViewWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityCustomBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.selectFileButton.setOnClickListener(v -> {
            showPano1();
        });
    }

    private void showPano1() {

        final File file = new File(getFilesDir(), "1024.jpg");
        assert file.exists();

        Pano360ConfigBundle.newInstance()
                .setMimeType(MimeType.LOCAL_FILE)
                .setFilePath(file.getAbsolutePath())
//                .setFilePath("images/texture_360_n.jpg")
                .setRemoveHotspot(true)
                .startEmbeddedActivity(this);
    }

    private void showPano2() {
        /*GLSurfaceView glSurfaceView = mBinding.glSurfaceView;

        Pano360ConfigBundle configBundle = Pano360ConfigBundle.newInstance()
                .setMimeType(MimeType.ASSETS)
                .setFilePath("images/texture_360_n.jpg")
                .setRemoveHotspot(true);


        mPanoViewWrapper = PanoViewWrapper.with(this)
                .setConfig(configBundle)
                .setGlSurfaceView(glSurfaceView)
                .init();

        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mPanoViewWrapper.handleTouchEvent(event);
            }
        });*/
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (null != mPanoViewWrapper)
            mPanoViewWrapper.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (null != mPanoViewWrapper)
            mPanoViewWrapper.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (null != mPanoViewWrapper)
            mPanoViewWrapper.releaseResources();
    }
}