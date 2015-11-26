package com.xiaopeng.funnyhello.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xiaopeng.funnyhello.R;
import com.xiaopeng.funnyhello.activity.SecurityAnimActivity;

import java.lang.ref.WeakReference;
import java.util.BitSet;

/**
 * Created by jerrypxiao on 15/11/26.
 */
public class CustomFrameLayout extends FrameLayout{
    private ImageView cleanupImageView1, cleanupImageView2;
    public static final float SCALE_LEVEL=1.7f;
    private ClearListener clearListener;
    private ImageView animationDrawableImageView;
    private AnimationDrawable animationDrawable;
    private ObjectAnimator scanAnimator;
    public BitSet  isScan=new  BitSet(2);
    public BitSet  isClear=new BitSet(2);

    public CustomFrameLayout(Context context){
        super(context);
    }
    public CustomFrameLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public CustomFrameLayout(Context context, AttributeSet attrs, int defstyle){
        super(context, attrs, defstyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        cleanupImageView1 = (ImageView) findViewById(R.id.cleanupImageView1);
        cleanupImageView2 = (ImageView) findViewById(R.id.cleanupImageView2);

        LayoutParams lp=new LayoutParams(cleanupImageView1.getLayoutParams());
        int diameter = SecurityAnimActivity.screenWidth*705/1080*4/5;
        lp.width=diameter;
        lp.height=diameter;
        lp.gravity= Gravity.CENTER;
        cleanupImageView1.setLayoutParams(lp);

        LayoutParams lp2=new LayoutParams(cleanupImageView2.getLayoutParams());
        diameter = SecurityAnimActivity.screenWidth*654/1080*4/5;
        lp2.width=diameter;
        lp2.height=diameter;
        lp2.gravity=Gravity.CENTER;
        cleanupImageView2.setLayoutParams(lp2);



        cleanupImageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if((isScan.get(0)&isScan.get(1))||(isClear.get(0)&isClear.get(1))){
                    startProgressAnimator();
                    if(clearListener!=null)
                        clearListener.onClickClear();
                }

            }

        });

        animationDrawableImageView=(ImageView) findViewById(R.id.animationDrawableImageView);

        LayoutParams lp3=new LayoutParams(animationDrawableImageView.getLayoutParams());
        diameter=diameter*4/5;
        lp3.width=diameter;
        lp3.height=diameter;
        lp3.gravity=Gravity.CENTER;
        animationDrawableImageView.setLayoutParams(lp3);

        startScanAnimator();
    }

    private void startScanAnimator() {
        scanAnimator= ObjectAnimator.ofFloat(animationDrawableImageView, View.ROTATION, 0, 360);
        scanAnimator.setDuration(1000);
        scanAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scanAnimator.setInterpolator(new LinearInterpolator());
        scanAnimator.addListener(new MyAnimatorListener(this));
        scanAnimator.start();
    }

    static class MyAnimatorListener implements Animator.AnimatorListener {
        WeakReference<CustomFrameLayout> mLayout;
        MyAnimatorListener(CustomFrameLayout l) {
            mLayout = new WeakReference<CustomFrameLayout>(l);
        }
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            CustomFrameLayout l = mLayout.get();
            if (l != null) {
                l.animationDrawableImageView.setImageResource(R.drawable.clear_static);
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }
    }

    public void startProgressAnimator() {
        animationDrawableImageView.setBackgroundResource(R.drawable.clear_anim);
        animationDrawable=(AnimationDrawable) animationDrawableImageView.getBackground();
        animationDrawableImageView.setImageBitmap(null);
        animationDrawable.start();
    }

    public void stopRing(){
        if(animationDrawable!=null){
            animationDrawable.stop();
            animationDrawableImageView.setBackgroundResource(0);
            animationDrawableImageView.setImageResource(R.drawable.clear_static);
        }
        scanAnimator.end();
    }

    public interface ClearListener{
        void onClickClear();
    }

    public void setClearListener(ClearListener clearListener) {
        this.clearListener = clearListener;
    }

}
