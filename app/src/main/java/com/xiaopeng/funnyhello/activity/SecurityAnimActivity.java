package com.xiaopeng.funnyhello.activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.xiaopeng.funnyhello.R;
import com.xiaopeng.funnyhello.view.CustomFrameLayout;

public class SecurityAnimActivity extends Activity {
    public static int screenWidth, screenHeight;
    private LinearLayout clearlayout;
    private CustomFrameLayout clearFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_anim);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        clearFrameLayout = (CustomFrameLayout) findViewById(R.id.clearFrameLayout);
        clearFrameLayout.isScan.clear();
        clearFrameLayout.isClear.clear();
        clearFrameLayout
                .setClearListener(new CustomFrameLayout.ClearListener() {

                    @Override
                    public void onClickClear() {

                        //oneKeyClear();
                        clearFrameLayout.isScan.clear();

                    }
                });
    }


}
