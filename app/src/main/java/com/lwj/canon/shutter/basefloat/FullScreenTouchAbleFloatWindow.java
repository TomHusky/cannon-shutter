package com.lwj.canon.shutter.basefloat;

import android.content.Context;
import android.widget.Toast;

import com.lwj.canon.shutter.R;


/**
 * @author sun on 2018/12/26.
 */
public class FullScreenTouchAbleFloatWindow extends AbsFloatBase {

    public FullScreenTouchAbleFloatWindow(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = FULLSCREEN_TOUCHABLE;

        inflate(R.layout.main_layout_float_full_screen_touch_able);

        findView(R.id.btn_close).setOnClickListener(v -> {
            Toast.makeText(mContext, "remove", Toast.LENGTH_SHORT).show();
            remove();
        });
    }

    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
