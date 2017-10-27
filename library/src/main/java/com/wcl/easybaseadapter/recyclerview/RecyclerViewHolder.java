package com.wcl.easybaseadapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangchunlong on 2017/10/27.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private View contentView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.contentView = itemView;
    }

    public View getContentView() {
        return contentView;
    }

    public <T> T getView(int resId){
        View view = contentView.findViewById(resId);
        if(view == null) return null;
        return (T)view;
    }
}
