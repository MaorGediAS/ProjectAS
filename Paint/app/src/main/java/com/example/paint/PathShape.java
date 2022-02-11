
package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;

public class PathShape extends Shape {

    public Path path = new Path();

    private int xEnd;
    private int yEnd;
    private MotionEvent event;
    public PathShape(int x, int y, String color, MotionEvent event) {
        super(x, y, color);
        this.event = event;
        xEnd = x;
        yEnd = y;
        path.moveTo(x,y);
    }

    @Override
    public void updatePoint(int xe, int ye) {
            xEnd = xe;
            yEnd = ye;
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            path.moveTo(x,y);}
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        if(MotionEvent.ACTION_MOVE== event.getAction() ){
            path.lineTo(xEnd,yEnd);
        }
        canvas.drawPath(path,paint);
    }
}