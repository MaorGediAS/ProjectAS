package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class CircleShape extends BiggestShape {

    private int xEnd;
    private int yEnd;
    private float yCenter,xCenter;
    private double R;
    private boolean IsFilled;

    public CircleShape(int x, int y, String color,boolean isFilled) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        IsFilled = isFilled;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        xCenter = (xe + x) / 2;
        yCenter = (ye + y) / 2;
        R = Math.sqrt((xEnd-x)*(xEnd-x)+(yEnd-y)*(yEnd - y));
    }
    @Override
    public double GetSurface(){
        return Math.pow(R,2)*3.14;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(8);
        if(IsFilled == true) {
            paint.setStyle(Paint.Style.FILL);
        }if(IsFilled == false) {
            paint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawCircle(xCenter,yCenter,(float) R/2,paint);
    }
}