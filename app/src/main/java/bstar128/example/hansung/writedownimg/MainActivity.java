package bstar128.example.hansung.writedownimg;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton playbut;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playbut=(ImageButton)findViewById(R.id.playbut);
        playbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent=new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });
    }
}
