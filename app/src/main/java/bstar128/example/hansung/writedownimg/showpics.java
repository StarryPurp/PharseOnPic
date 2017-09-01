package bstar128.example.hansung.writedownimg;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
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
    ImageButton start;
    ImageView album;
    Dialog d;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpics);
        start = (ImageButton) this.findViewById(R.id.playbut);
        album = (ImageView) this.findViewById(R.id.album);
    }

    private void SelectGallery() {//갤러리 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {//경로 체크
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    private String getPath(Uri selectedImageUri) {//이미지 절대 경로
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor c = managedQuery(selectedImageUri, proj, null, null, null);
        int column_index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (c != null) {
            c.moveToFirst();
            return c.getString(column_index);
        }
        return selectedImageUri.getPath();

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



