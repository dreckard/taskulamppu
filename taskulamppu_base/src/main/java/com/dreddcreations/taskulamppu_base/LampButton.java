package com.dreddcreations.taskulamppu_base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class LampButton extends ImageButton {


    public LampButton(Context context) {
        super(context);
    }

    public LampButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LampButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public LampButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w,h,oldw,oldh);
        /*Matrix m = new Matrix();
        //RectF drawableRect = new RectF(0, 0, image_width, image_height);
        float image_width = getDrawable().getIntrinsicWidth();
        float image_height = getDrawable().getIntrinsicHeight();
        RectF drawableRect = new RectF(0,0,image_width,image_height);
        Rect viewRect = new Rect();
        //getDrawingRect(drawableRect);
        getDrawingRect(viewRect);
        //viewRect.top = 0;
        //RectF viewRect = new RectF(0, 0, screen_width, screen_height);
        m.setRectToRect(drawableRect, new RectF(viewRect), Matrix.ScaleToFit.CENTER);
        m.preScale(1,image_height/(viewRect.bottom-viewRect.top),image_width/2,image_height/2);
        //m.postSkew
        //m.postRotate(10,image_width/2,image_height/2);
        //m.postScale(0.5,0.5);
        setScaleType(ScaleType.MATRIX);
        //m.postRotate(180);
        setImageMatrix(m);*/
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //getResources().getDrawable(R.drawable.lamp_on).
    }
}
