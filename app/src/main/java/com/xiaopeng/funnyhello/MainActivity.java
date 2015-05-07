package com.xiaopeng.funnyhello;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xiaopeng.funnyhello.utils.DrawerLayoutInstaller;
import com.xiaopeng.funnyhello.utils.Utils;
import com.xiaopeng.funnyhello.view.GlobalMenuView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private MenuItem inboxMenuItem;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setupToolbar();
        setupDrawer();
        try {
            setupList();
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getApplicationContext(),"��ȡactivity�б�ʧ��", Toast.LENGTH_LONG).show();
        }
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    private void setupDrawer() {
        GlobalMenuView menuView = new GlobalMenuView(this);
        //menuView.setOnHeaderClickListener(this);

        drawerLayout = DrawerLayoutInstaller.from(this)
                .drawerRoot(R.layout.drawer_root)
                .drawerLeftView(menuView)
                .drawerLeftWidth(Utils.dpToPx(300))
                .withNavigationIconToggler(getToolbar())
                .build();
    }

    private void setupList() throws PackageManager.NameNotFoundException{
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        ActivityInfo[] infos = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES)
                .activities;

        ArrayList<ActivityInfo> infoArrayList = new ArrayList<>();

        for(ActivityInfo info : infos){
            if(info.name.startsWith("com.xiaopeng.funnyhello.activity")){
                infoArrayList.add(info);
            }
        }

        MyAdapter adapter = new MyAdapter(this, infoArrayList);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);
        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
