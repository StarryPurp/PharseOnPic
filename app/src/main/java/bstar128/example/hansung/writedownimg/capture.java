package bstar128.example.hansung.writedownimg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by HANSUNG on 2017-09-05.
 */

//배경에 있는 이미지를 한번 탭하면 캡쳐 버튼이 떠서 그 화면이 캡쳐되어서 갤러리에 저장되는 작업

public class capture extends Activity {
    Button c;
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//버튼 눌렀을때 캡쳐되어서 저장되는것
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture);
        c=(Button)findViewById(R.id.capture);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getApplicationContext(),showpics.class);
            }
        });
    }
}
