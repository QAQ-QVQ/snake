package com.yu.snake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SnakePanelView snakePanelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        snakePanelView = findViewById(R.id.snake_view);
//        snakePanelView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        snakePanelView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snakePanelView.StartGame();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
