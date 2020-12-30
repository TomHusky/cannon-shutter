package com.lwj.canon.shutter.basefloat;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.lwj.canon.shutter.R;


/**
 * @author sun on 2018/12/27.
 */
public class FloatPermissionDetectView extends AbsFloatBase {

    public FloatPermissionDetectView(Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();

        mViewMode = WRAP_CONTENT_TOUCHABLE;

        mGravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        inflate(R.layout.main_layout_float_permission_detect);

        findView(R.id.btn_close).setOnClickListener(v -> remove());
    }

    @Override
    protected void onAddWindowFailed(Exception e) {

    }
}
