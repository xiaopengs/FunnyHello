package com.xiaopeng.funnyhello.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.xiaopeng.funnyhello.utils.TouchEventUtil;

/**
 * Created by xiaopeng on 2015/5/19.
 */
public class TouchTestViewChild extends LinearLayout {

    public TouchTestViewChild(Context context) {
        super(context);
    }

    public TouchTestViewChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("xiaopeng", "TouchEventChild | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("xiaopeng", "TouchEventChild | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("xiaopeng", "TouchEventChild | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onTouchEvent(ev);
    }

}
