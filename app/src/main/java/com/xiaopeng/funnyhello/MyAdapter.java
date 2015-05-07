package com.xiaopeng.funnyhello;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/5/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;

    private List<ActivityInfo> infoes;

    public MyAdapter(Context c, List<ActivityInfo> infoes) {
        super();
        mContext = c;
        this.infoes = infoes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        //View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false);


        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if (infoes.get(i).labelRes > 0) {
            viewHolder.mTextView.setText(infoes.get(i).labelRes);
            final String targetActivity = infoes.get(i).name;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(mContext, Class.forName(targetActivity));
                        mContext.startActivity(it);
                    } catch (ClassNotFoundException e) {
                        Log.e("sunfei", " Activity :" + targetActivity + " is not a valid activity");
                    }
                }
            });

        }else {
            //no label for:android.support.v7.widget.TestActivity
            Log.e("sunfei","no label for:" + infoes.get(i).name);
        }
    }

    @Override
    public int getItemCount() {
        return infoes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View viewRoot) {
            super(viewRoot);
            mTextView = (TextView) viewRoot.findViewById(R.id.card_text);
        }
    }
}
