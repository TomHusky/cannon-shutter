package com.lwj.canon.shutter.basefloat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.blankj.utilcode.util.SizeUtils;
import com.lwj.canon.shutter.MainActivity;
import com.lwj.canon.shutter.R;


/**
 * Create by sun on 2020/7/31 5:16 PM
 */
public class FollowTouchView extends AbsFloatBase {

    private final int mScaledTouchSlop;

    public FollowTouchView(Context context) {
        super(context);

        mViewMode = WRAP_CONTENT_TOUCHABLE;

        mGravity = Gravity.START | Gravity.TOP;

        mAddX = SizeUtils.dp2px(100);
        mAddY = SizeUtils.dp2px(100);

        inflate(R.layout.main_layout_follow_touch);

        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mInflate.setOnTouchListener(new View.OnTouchListener() {

            private float mLastY;
            private float mLastX;
            private float mDownY;
            private float mDownX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getRawX();
                float y = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = x;
                        mDownY = y;
                        mLastX = x;
                        mLastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        float moveX = x - mLastX;
                        float moveY = y - mLastY;

                        Log.e("TAG", moveX + " " + moveY);

                        mLayoutParams.x += moveX;
                        mLayoutParams.y += moveY;

                        mWindowManager.updateViewLayout(mInflate, mLayoutParams);

                        mLastX = x;
                        mLastY = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        float disX = x - mDownX;
                        float disY = y - mDownY;
                        double sqrt = Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
                        if (sqrt < mScaledTouchSlop) {
                            jumpHome();
                        }
                        break;
                }

                return false;
            }
        });
    }

    private void jumpHome() {
        Toast.makeText(mContext, "点击测试", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        mContext.startActivity(intent);
    }


    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
