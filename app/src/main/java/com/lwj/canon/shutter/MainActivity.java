package com.lwj.canon.shutter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lwj.canon.shutter.basefloat.FloatWindowParamManager;
import com.lwj.canon.shutter.basefloat.RomUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_check_permission).setOnClickListener(v -> {
            boolean permission = FloatWindowParamManager.checkPermission(getApplicationContext());
            if (permission && !RomUtils.isVivoRom()) {
                Toast.makeText(MainActivity.this, R.string.has_float_permission, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
                intent.setAction(FloatWindowService.ACTION_CHECK_PERMISSION_AND_TRY_ADD);
                startService(intent);
            } else {
                Toast.makeText(MainActivity.this, R.string.no_float_permission, Toast.LENGTH_SHORT).show();
                showOpenPermissionDialog();
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            stopService(intent);
        });

        findViewById(R.id.btn_full_screen_touch_able).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_FULL_SCREEN_TOUCH_ABLE);
            startService(intent);
        });

        findViewById(R.id.btn_full_screen_touch_disable).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_FULL_SCREEN_TOUCH_DISABLE);
            startService(intent);
        });

        findViewById(R.id.btn_not_full_touch_able).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_NOT_FULL_SCREEN_TOUCH_ABLE);
            startService(intent);
        });

        findViewById(R.id.btn_not_full_touch_disable).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_NOT_FULL_SCREEN_TOUCH_DISABLE);
            startService(intent);
        });

        findViewById(R.id.btn_input).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_INPUT);
            startService(intent);
        });

        findViewById(R.id.btn_anim).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_ANIM);
            startService(intent);
        });

        findViewById(R.id.btn_touch_follow).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_FOLLOW_TOUCH);
            startService(intent);
        });


    }

    private void showOpenPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.no_float_permission);
        builder.setMessage(R.string.go_t0_open_float_ask);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            FloatWindowParamManager.tryJumpToPermissionPage(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
            intent.setAction(FloatWindowService.ACTION_CHECK_PERMISSION_AND_TRY_ADD);
            startService(intent);
        });

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(getApplicationContext(), FloatWindowService.class);
        stopService(intent);
    }
}
