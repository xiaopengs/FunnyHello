package com.xiaopeng.funnyhello.activity;

import android.content.pm.PackageInfo;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lody.plugin.LPluginOpener;
import com.lody.plugin.api.LPluginBug;
import com.lody.plugin.api.LPluginBugListener;
import com.lody.plugin.manager.LPluginBugManager;
import com.xiaopeng.funnyhello.R;
import com.xiaopeng.funnyhello.utils.Utils;

import java.io.File;
import java.util.ArrayList;

public class Demo2Activity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ArrayList<PluginItem> mPluginItems = new ArrayList<PluginItem>();
    private PluginAdapter mPluginAdapter;

    private ListView mListView;
    private TextView mNoPluginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        initView();
        initData();
    }

    private void initView() {
        mPluginAdapter = new PluginAdapter();
        mListView = (ListView) findViewById(R.id.plugin_list);
        //mNoPluginTextView = (TextView)findViewById(R.id.no_plugin);
    }

    private void initData() {
        String pluginFolder = Environment.getExternalStorageDirectory() + "/DLA";
        Log.i("xiaopeng","pluginFolder = " + pluginFolder);
        File file = new File(pluginFolder);
        File[] plugins = file.listFiles();
        if (plugins == null || plugins.length == 0) {
            //mNoPluginTextView.setVisibility(View.VISIBLE);
            return;
        }

        for (File plugin : plugins) {
            PluginItem item = new PluginItem();
            item.pluginPath = plugin.getAbsolutePath();
            Log.i("xiaopeng","pluginPath = " + item.pluginPath);
            item.packageInfo = Utils.getPackageInfo(this, item.pluginPath);

            if (item.packageInfo != null && item.packageInfo.activities != null && item.packageInfo.activities.length > 0) {
                item.launcherActivityName = item.packageInfo.activities[0].name;
            }
            if (item.packageInfo != null && item.packageInfo.services != null && item.packageInfo.services.length > 0) {
                item.launcherServiceName = item.packageInfo.services[0].name;
            }
            mPluginItems.add(item);
            //DLPluginManager.getInstance(this).loadApk(item.pluginPath);
        }

        mListView.setAdapter(mPluginAdapter);
        mListView.setOnItemClickListener(this);
        mPluginAdapter.notifyDataSetChanged();
    }


    private class PluginAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public PluginAdapter() {
            mInflater = Demo2Activity.this.getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mPluginItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mPluginItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.plugin_item, parent, false);
                holder = new ViewHolder();
                holder.appIcon = (ImageView) convertView.findViewById(R.id.app_icon);
                holder.appName = (TextView) convertView.findViewById(R.id.app_name);
                holder.apkName = (TextView) convertView.findViewById(R.id.apk_name);
                holder.packageName = (TextView) convertView.findViewById(R.id.package_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PluginItem item = mPluginItems.get(position);
            PackageInfo packageInfo = item.packageInfo;
            holder.appIcon.setImageDrawable(Utils.getAppIcon(Demo2Activity.this, item.pluginPath));
            holder.appName.setText(Utils.getAppLabel(Demo2Activity.this, item.pluginPath));
            holder.apkName.setText(item.pluginPath.substring(item.pluginPath.lastIndexOf(File.separatorChar) + 1));
            if(packageInfo != null) {
                holder.packageName.setText(packageInfo.applicationInfo.packageName + "\n" +
                        item.launcherActivityName + "\n" +
                        item.launcherServiceName);
            }
            return convertView;
        }
    }

    private static class ViewHolder {
        public ImageView appIcon;
        public TextView appName;
        public TextView apkName;
        public TextView packageName;
    }

    public static class PluginItem {
        public PackageInfo packageInfo;
        public String pluginPath;
        public String launcherActivityName;
        public String launcherServiceName;

        public PluginItem() {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PluginItem item = mPluginItems.get(position);
        //DLPluginManager pluginManager = DLPluginManager.getInstance(this);
        //pluginManager.startPluginActivity(this, new DLIntent(item.packageInfo.packageName, item.launcherActivityName));
//        LPluginBugManager.addBugListener(new LPluginBugListener() {
//            @Override
//            public void OnError(LPluginBug bug) {
//
//                Log.e("DEBUG", bug.error.getMessage());
//                android.os.Process.killProcess(bug.processId);
//                System.exit(10);
//            }
//        });
//        LPluginOpener.startPlugin(RunApkFromSdcard.this, path);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
