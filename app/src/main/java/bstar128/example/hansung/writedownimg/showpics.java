package bstar128.example.hansung.writedownimg;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class showpics extends Activity implements View.OnClickListener{
    ImageView album;
    Dialog d;
    EditText phrase;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private  final int REQ_CODE=200;
    private Dialog di;
    private TextView tv;
    float previus_x=0;
    float previus_y=0;
    Vibrator mVibe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpics);
        album = (ImageView) this.findViewById(R.id.album);
        tv = (TextView) this.findViewById(R.id.text3);
        SelectGallery();
        di=new Dialog(this);
        di.requestWindowFeature(Window.FEATURE_NO_TITLE);
        di.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        di.setContentView(R.layout.makebg);
        di.show();//배경 흐리게 만들기


        phrase=(EditText)di.findViewById(R.id.phrase);

        // phrase.setImeOptions(EditorInfo.IME_ACTION_DONE);
        phrase.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {
                    tv.setText(((EditText) v).getText());
                    tv.setVisibility(View.VISIBLE);
                    di.hide();
                }
                return false;
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.INVISIBLE);
                di.show();

            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mVibe=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }


    private void SelectGallery() {//갤러리 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,REQ_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    String name_str=getPath(data.getData());
                    Bitmap img_bit= MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    album.setImageBitmap(img_bit);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                }
            }
        }
    public String getPath(Uri selectedImageUri) {//이미지 절대 경로
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor c = managedQuery(selectedImageUri, proj, null, null, null);
        int column_index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            c.moveToFirst();
            String imgPath=c.getString(column_index);
            String imgName=imgPath.substring(imgPath.lastIndexOf("/")+1);
            return imgName;
    }

    private void SendPicture(Intent data){
        Uri imguri=data.getData();
        String imagePath=getPath(imguri);
        ExifInterface exif=null;
        try{
            exif=new ExifInterface(imagePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int exifOrientionToDegrees(int exifOriention) {//각도
        if (exifOriention == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOriention == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOriention == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {//TextView 옮기기

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case  MotionEvent.ACTION_MOVE:
                int touch_x=(int)event.getX();
                int touch_y=(int)event.getY();
                ObjectAnimator textX=ObjectAnimator.ofFloat(tv,"translationX",previus_x,touch_x);
                textX.start();
                ObjectAnimator textY=ObjectAnimator.ofFloat(tv,"translationY",previus_y,touch_y);
                textY.start();
                mVibe.vibrate(30);

                previus_x=touch_x;
                previus_y=touch_y;
                break;

            case MotionEvent.ACTION_UP:
                break;

        }
        return false;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.select){
            AlertDialog.Builder b=new AlertDialog.Builder(this);
            View imglayout=View.inflate(this,R.layout.showpics,null);
            b.setView(imglayout);
            imglayout.findViewById(R.id.select).setOnClickListener(this);

            d=b.show();
            d.show();

        }
        SelectGallery();
    }
}//앨범사진 띄우기



