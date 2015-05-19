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
public class TouchTestView extends LinearLayout {

    public TouchTestView(Context context) {
        super(context);
    }

    public TouchTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("xiaopeng", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("xiaopeng", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("xiaopeng", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onTouchEvent(ev);
    }

}
