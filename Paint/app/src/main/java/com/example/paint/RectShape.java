package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends BiggestShape {

    private int xEnd;
    private int yEnd;
    private boolean isFill;

    public RectShape(int x, int y, String color,boolean filler) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        isFill =filler;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }
    @Override
    public double GetSurface(){
        return Math.abs(x-xEnd)*Math.abs(y-yEnd);
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(8);
        if(isFill == true) {
            paint.setStyle(Paint.Style.FILL);
        }if(isFill == false) {
            paint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawRect(x,y,xEnd,yEnd,paint);
    }
}
