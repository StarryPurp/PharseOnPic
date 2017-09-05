package bstar128.example.hansung.writedownimg;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HANSUNG on 2017-09-02.
 */

public class makebg extends Activity{
    EditText text;
    Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win=getWindow();
        win.setContentView(R.layout.showpics);
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.makebg,null);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
        );
        win.addContentView(linearLayout,params);
        texting();
        setTitle("글귀 적기기");
    }



    private void texting() {
        text=(EditText)findViewById(R.id.phrase);
        text.getText();
    }


    //뒤의 사진배경을 흐르게 효과주고 그 위에 글 적기
}