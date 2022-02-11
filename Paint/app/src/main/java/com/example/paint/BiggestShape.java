package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Stack;

public abstract class BiggestShape extends  Shape{

    protected int x;
    protected int y;
    protected String color;

    public BiggestShape(int x, int y, String color) {
        super(x,y,color);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract double GetSurface();
    public abstract void updatePoint(int xe,int ye);

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor(color));
    }

}
