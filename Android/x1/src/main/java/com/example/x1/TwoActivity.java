package com.example.x1;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TwoActivity extends AppCompatActivity {

    int time=5;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //获取控件
        ImageView imageView = findViewById(R.id.imageView_dao);
        button = findViewById(R.id.button_dao);

        handler.sendEmptyMessage(0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwoActivity.this,ThreeActivity.class));
                finish();
            }
        });

    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 0:

                    time--;
                    button.setText("跳过"+time+"s");
                    if (time<=0) {
                        startActivity(new Intent(TwoActivity.this,ThreeActivity.class));
                        finish();
                    }

                    sendEmptyMessageDelayed(0,1000);

                    break;
            }
        }
    };//创建hander


    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeMessages(0);
    }
}
