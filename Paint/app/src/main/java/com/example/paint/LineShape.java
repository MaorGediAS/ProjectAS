package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LineShape extends Shape {

    private int xEnd;
    private int yEnd;
    public  int Thickness;

    public LineShape(int x, int y, String color,int thickness) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        Thickness= thickness;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(Thickness);
        canvas.drawLine(x,y,xEnd,yEnd,paint);
    }
}
