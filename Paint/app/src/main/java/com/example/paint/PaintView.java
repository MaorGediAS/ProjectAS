package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {

    public boolean IfF;
    private Paint paint;
    private Paint bgPaint;
    private String currentShape ="Rect";
    private String currentColor = "#FFFFFFFF";
    private int thickness=10;
    public boolean TheN = false;

    private Stack<Shape> shapes;
    private Stack<Shape> nShapes;
    Button WBTN;
    EditText WidthN;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        paint = new Paint();
        WBTN = (Button)findViewById(R.id.WidthBtn);
        WidthN = (EditText)findViewById(R.id.PicName);
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        nShapes = new Stack<>();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12);
        bgPaint.setColor(Color.rgb(255,255,255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }

    Shape shape;



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor,IfF);

            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor,thickness);

            }
            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor,IfF);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor,event);
            }
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }

        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }
    public void SetWidth()
    {
       thickness = thickness + 10;
    }
    public void Fill(){
        if(IfF==true){
            IfF = false;
        }
        else{
            IfF = true;
        }
    }


    public void undo()
    {
        if (!shapes.empty()){
            shapes.pop();
            invalidate();
        }
    }

    public void pathUndo(){
        while(!shapes.empty()){
            if(shapes.peek() instanceof PathShape){
                shapes.pop();
            }
            else {
                nShapes.push(shapes.pop());
            }
        }
        while(!nShapes.empty()) {
            shapes.push(nShapes.pop());
        }
        invalidate();

    }
    public void Bigest(){
        double area = 0;
        TheN=false;
        BiggestShape temp = null;
        while(!shapes.empty()){
            if(shapes.peek()instanceof BiggestShape){
                if(area < ((BiggestShape)shapes.peek()).GetSurface()){
                    area = ((BiggestShape)shapes.peek()).GetSurface();
                    temp = (BiggestShape) shapes.peek();
                }
            }
            shapes.pop();
        }
        if(temp!=null){
            shapes.push(temp);
            TheN = false;
        }
        else{
            TheN=true;
        }
        if(shapes.empty()){
            TheN = false;
        }
        invalidate();
    }

}