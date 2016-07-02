package com.dreamer.likesinasportprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SinaSportLayout mSinaSportLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSinaSportLayout = (SinaSportLayout) findViewById(R.id.layout);
        mSinaSportLayout.setOnSinaSportListener(new SinaSportLayout.OnSinaSportListener() {
            @Override
            public void onLeftClick(TextView tvLeft) {
                tvLeft.setText(mSinaSportLayout.getLeftProgressValue() + "");
            }

            @Override
            public void onRightClick(TextView tvRight) {
                tvRight.setText(mSinaSportLayout.getRightProgressValue() + "");
            }
        });
    }
}
