package com.ebrightmoon.security;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin.apk";
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxPermissions = new RxPermissions(this);
    }

    public void click(View view) {
         rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
         .subscribe(granted->
         {
             if (granted) { // Always true pre-M
//                        // I can control the camera now
                        Intent intent = new Intent(this, ProxyActivity.class);
                        intent.putExtra(ProxyActivity.EXTRA_DEX_PATH, path);
                        startActivity(intent);
                    } else {
//                        // Oups permission denied
                    }
         });

//        rxPermissions
//                .request(Manifest.permission.CAMERA)
//                .subscribe(granted -> {
//                    if (granted) { // Always true pre-M
//                        // I can control the camera now
//                        Intent intent = new Intent(this, ProxyActivity.class);
//                        intent.putExtra(ProxyActivity.EXTRA_DEX_PATH, path);
//                        startActivity(intent);
//                    } else {
//                        // Oups permission denied
//                    }
//                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }
}
