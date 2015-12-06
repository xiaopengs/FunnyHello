package com.xiaopeng.funnyhello.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jerrypxiao on 15/11/26.
 */
public class LoadingFrameLayout extends ImageView {
    //Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE = 20;

    private Paint mArcPaint;
    private Paint mCirclePaint;
    private int mCircleWidth;


    public LoadingFrameLayout(Context context){
        super(context);
        init();
    }
    public LoadingFrameLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    public LoadingFrameLayout(Context context, AttributeSet attrs, int defstyle){
        super(context, attrs, defstyle);
        init();
    }

    private void init(){
        mCircleWidth = 20;//dp2px(DEFAULT_SIZE);
        final int padding = mCircleWidth;
        setPadding(padding, padding, padding, padding);
        createPaint();
    }

    private void createPaint(){
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(mCircleWidth);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("xiaopeng", "xiaopeng getMeasuredWidth() = " + getMeasuredWidth());
        Log.i("xiaopeng", "xiaopeng getMeasuredHeight() = " + getMeasuredHeight());
        //setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.i("xiaopeng ","xiaopeng draw");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("xiaopeng ","xiaopeng onDraw");
        final int viewWidth = getWidth();
        final int viewHeight = getHeight();

        Log.i("xiaopeng ","xiaopeng onDraw viewWidth = " + viewWidth + ", viewHeight = " + viewHeight);

        //canvas.drawArc(new RectF(0,0,viewWidth,viewHeight), 0, 360, false, mArcPaint);
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth / 2 - mCircleWidth /2,
                mArcPaint);
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }


}
