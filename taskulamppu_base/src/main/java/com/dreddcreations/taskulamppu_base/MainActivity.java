package com.dreddcreations.taskulamppu_base;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Camera mCamera = null;
    private ImageButton mToggleBtn = null;
    private SurfaceView mSurfaceView = null;
    private boolean mLampEnabled = false;

    public boolean initCamera() {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.startPreview();
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
            Toast toast = Toast.makeText(getApplicationContext(), "Error: Failed to start camera preview", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }
    public void closeCamera() {
        if ( mCamera != null )
        {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    public void enableLamp() {
        if ( mCamera != null ) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(params);
            mToggleBtn.setImageDrawable(getResources().getDrawable(R.drawable.lamp_on));
            //mToggleBtn.setText(R.string.toggle_text_on);
        }
    }
    public void disableLamp() {
        if ( mCamera != null ) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(params);
            mToggleBtn.setImageDrawable(getResources().getDrawable(R.drawable.lamp_off));
            //mToggleBtn.setText(R.string.toggle_text_off);
        }
    }
    public void toggleLamp() {
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
        mSurfaceView.setKeepScreenOn(true);
        mSurfaceView.setVisibility(SurfaceView.GONE);
        mToggleBtn = (ImageButton) findViewById(R.id.toggle_btn);
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
