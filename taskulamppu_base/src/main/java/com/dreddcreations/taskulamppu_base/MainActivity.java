package com.dreddcreations.taskulamppu_base;

import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Camera mCamera = null;
    private ImageButton mToggleBtn = null;
    private SurfaceView mSurfaceView = null;
    private boolean mLampEnabled = false;
    private Drawable mLampOffDrawable = null;
    private Drawable mLampOnDrawable = null;

    private boolean initCamera() {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.startPreview();
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
            Toast toast = Toast.makeText(getApplicationContext(), R.string.camera_not_available, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }
    private void closeCamera() {
        if ( mCamera != null )
        {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    private void enableLamp() {
        if ( mCamera != null ) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(params);
            mToggleBtn.setImageDrawable(mLampOnDrawable);
            //mToggleBtn.setText(R.string.toggle_text_on);
        }
    }
    private void disableLamp() {
        if ( mCamera != null ) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(params);
            mToggleBtn.setImageDrawable(mLampOffDrawable);
            //mToggleBtn.setText(R.string.toggle_text_off);
        }
    }
    private void toggleLamp() {
        if (!mLampEnabled) {
            enableLamp();
            mLampEnabled = true;
        }
        else {
            disableLamp();
            mLampEnabled = false;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initCamera();
        if ( mLampEnabled )
            enableLamp();
        else
            disableLamp();

        //mCamera = Camera.open();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        closeCamera();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        //mSurfaceView.setKeepScreenOn(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mSurfaceView.setVisibility(SurfaceView.GONE);
        mToggleBtn = (ImageButton) findViewById(R.id.toggle_btn);
        mLampOffDrawable = getResources().getDrawable(R.drawable.lamp_off);
        mLampOnDrawable = getResources().getDrawable(R.drawable.lamp_on);
        mToggleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Toast toast = Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT);
                //toast.show();
                toggleLamp();
            }
        });
    }
}
