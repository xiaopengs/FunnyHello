package com.xiaopeng.funnyhello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.xiaopeng.funnyhello.lib.ShimmerFrameLayout;

/**
 * app的欢迎界面
 * @created 2015-05-21
 * @author xiaopeng
 *
 */
public class WelcomePage extends Activity {
	private Handler mHandler;
	private ShimmerFrameLayout mShimmerViewContainer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_welcome_page);

		mHandler = new Handler();
		mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);

//		//渐变展示启动屏
//		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
//		aa.setDuration(3000);
//		view.startAnimation(aa);
//		aa.setAnimationListener(new AnimationListener()
//		{
//			@Override
//			public void onAnimationEnd(Animation arg0) {
//				finish();
//				goMainActivity(WelcomePage.this);
//			}
//			@Override
//			public void onAnimationRepeat(Animation animation) {}
//			@Override
//			public void onAnimationStart(Animation animation) {}
//
//		});
	}

	@Override
	public void onResume() {
		super.onResume();
		mShimmerViewContainer.startShimmerAnimation();

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
				goMainActivity(WelcomePage.this);
			}
		}, 3000);
	}

	@Override
	public void onPause() {
		mShimmerViewContainer.stopShimmerAnimation();
		super.onPause();
	}

	/**
	 * 进入主界面
	 *
	 * @param context
	 */
	public static void goMainActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}
}
