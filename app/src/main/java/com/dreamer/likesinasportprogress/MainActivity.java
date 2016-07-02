package com.dreamer.likesinasportprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ratioprogress) {
            startActivity(new Intent(this, RatioProgressActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
