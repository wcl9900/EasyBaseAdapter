package com.wcl.easybaseadapter.viewholder;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>Describe:ItemView 基类缓存器
 * <p>Author:王春龙
 * <p>CreateTime:2017/7/31
 */
public class BaseEntityViewHolder {

    public ViewGroup viewGroup;
    public View itemView;
    private SparseArray<View> mapView;

    public BaseEntityViewHolder() {
        mapView = new SparseArray<View>();
    }

    public View findViewById(int id){
        View findView = mapView.get(id);
        if(findView == null){
            findView = itemView.findViewById(id);
            if(findView != null){
                mapView.put(id, findView);
            }
            return findView;
        }
        return findView;
    }
}
