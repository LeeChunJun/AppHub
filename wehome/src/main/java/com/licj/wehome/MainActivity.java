package com.licj.wehome;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;


public class MainActivity extends AppCompatActivity {
    private VrPanoramaView mVrPanoramaView;
    private VrPanoramaView.Options paNoramalOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVrPanoramaView = findViewById(R.id.mVrPanoramaView);
        paNoramalOption = new VrPanoramaView.Options();
        paNoramalOption.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

//        mVrPanoramaView.setFullscreenButtonEnabled(false);
        mVrPanoramaView.setInfoButtonEnabled(false);
        mVrPanoramaView.setStereoModeButtonEnabled(false);

        mVrPanoramaView.setEventListener(new ActivityEventListener());
        mVrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes), paNoramalOption);

//        加载网络图片
//        mVrPanoramaView.loadImageFromByteArray();
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();
        }

        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
        }

        @Override
        public void onClick() {
            super.onClick();
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
        }
    }
}
