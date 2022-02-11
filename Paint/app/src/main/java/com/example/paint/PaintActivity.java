package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;
import yuku.ambilwarna.AmbilWarnaDialog;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private int mDefaultColor;
    Button undoBtn;
    Button Surface;
    String imgSaved;
    EditText TextEdited;
    String ThePicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        TextEdited =(EditText)findViewById(R.id.PicName);
        mDefaultColor = ContextCompat.getColor(PaintActivity.this,R.color.design_default_color_primary);
        frame.addView(paintView);
        undoBtn = findViewById(R.id.btnUndoPath);
        Surface = findViewById(R.id.BiggestS);

    }
    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }
    public void WidthSetter(View view){paintView.SetWidth();}
    public void Filler(View view){paintView.Fill();}

    public void saveImage(View view) {
        paintView.setDrawingCacheEnabled(true);
        if(TextEdited.getText().toString()!=null){
            String ThePicName = TextEdited.getText().toString();
            Toast.makeText(this, ThePicName,
                    Toast.LENGTH_LONG).show();
            imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(),  paintView.getDrawingCache(), ThePicName+ ".jpg", "drawing");
            paintView.destroyDrawingCache();
        }
        else{
            String Name = TextEdited.getText().toString();
            imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(),  paintView.getDrawingCache(), UUID.randomUUID()+ ".jpg", "drawing");
            paintView.destroyDrawingCache();
        }


    }
    private void createDirectoryAndSaveFile(Canvas imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/DICM");
        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/DICM/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File("/sdcard/DirName/", fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.save();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ColorPicker(View view){
        openColorPicker();
    }

    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                String hexColor = String.format("#%06X",(0xFFFFFF & mDefaultColor));
                paintView.setColor(hexColor);
            }
        });
        colorPicker.show();
    }
    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
    public void TheBiggest(View view){
        if(paintView.TheN==true){
            toast();
        }
        else{
            paintView.Bigest();
        }
    }
    public void clearPath(View view){
        undoBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                paintView.pathUndo();
                return true;
            }
        });

    }
    public void toast(){
        Toast.makeText(PaintActivity.this,"No Draw Surface Shape",Toast.LENGTH_LONG).show();
    }

}

