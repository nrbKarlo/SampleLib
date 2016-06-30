package com.nerubia.samplelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Nerubia on 6/30/2016.
 */

public class CustomViewSample extends View {
    private int shapeColor;
    private boolean displayShapeName;
    private int shapeWidth = 100;
    private int shapeHeight = 100;
    private int textXOffset = 0;
    private int textYOffset = 30;
    private Paint paintShape;



    public CustomViewSample(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        shapeWidth=MeasureSpec.getSize(widthMeasureSpec);
        shapeHeight= MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(shapeWidth, shapeHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void setupAttributes(AttributeSet attrs) {
        // Obtain a typed array of attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViewSample, 0, 0);
        // Extract custom attributes into member variables
        try {
            shapeColor = a.getColor(R.styleable.CustomViewSample_shapeColor, Color.GRAY);
            displayShapeName = a.getBoolean(R.styleable.CustomViewSample_displayShapeName, false);
        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle();
        }
    }
    public boolean isDisplayingShapeName() {
        return displayShapeName;
    }

    public void setDisplayingShapeName(boolean state) {
        this.displayShapeName = state;
        invalidate();
        requestLayout();
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(int color) {
        this.shapeColor = color;
        invalidate();
        requestLayout();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, shapeWidth, shapeHeight, paintShape);
        if (displayShapeName) {
            canvas.drawText("Square", shapeWidth + textXOffset, shapeHeight + textXOffset, paintShape);
        }
    }

    private void setupPaint() {
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(shapeColor);
        paintShape.setTextSize(30);
    }
}