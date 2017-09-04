package bstar128.example.hansung.writedownimg;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

/**
 * Created by HANSUNG on 2017-09-02.
 */

public class makebg extends Activity{
    EditText text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        text=(EditText)findViewById(R.id.phrase);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makebg);

    }

    public void ImageViewing(){
        //갤러리에서 불러온 사진 띄우기
    }


    //뒤의 사진배경을 흐르게 효과주고 그 위에 글 적기
}
