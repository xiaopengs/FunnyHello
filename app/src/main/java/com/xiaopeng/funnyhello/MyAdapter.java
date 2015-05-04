package com.xiaopeng.funnyhello;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;

    private String[] mDataset;

    public MyAdapter(Context c,String[] dataset) {
        super();
        mContext = c;
        mDataset = dataset;
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

        viewHolder.mTextView.setText(mDataset[i]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View viewRoot) {
            super(viewRoot);
            mTextView = (TextView) viewRoot.findViewById(R.id.card_text);
        }
    }
}
