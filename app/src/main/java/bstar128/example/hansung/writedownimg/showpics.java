package bstar128.example.hansung.writedownimg;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

public class showpics extends Activity implements View.OnClickListener{
    ImageView album;
    Dialog d;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private  final int REQ_CODE=200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpics);
        album = (ImageView) this.findViewById(R.id.album);
        SelectGallery();
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
                    album.setImageBitmap(img_bit);//이미지가 안떠!!!
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



