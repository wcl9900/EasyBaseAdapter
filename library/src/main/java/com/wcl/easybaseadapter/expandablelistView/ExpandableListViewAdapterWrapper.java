package com.wcl.easybaseadapter.expandablelistView;

import android.view.View;
import android.widget.ExpandableListView;

import com.wcl.easybaseadapter.expandablelistView.listener.OnEntityExpandableListClickListener;

/**
 * <p>Describe:ExpandableListView 数据适配器的包装类
 * <p>Author:王春龙
 */
public class ExpandableListViewAdapterWrapper<T1, T2> implements
        ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener{

    private BaseEntityExpandableListAdapter<T1, T2> baseEntityExpandableListAdapter;

    private ExpandableListView expandableListView;

    private OnEntityExpandableListClickListener<T1, T2> onEntityExpandableListClickListener;

    public ExpandableListViewAdapterWrapper(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
        this.expandableListView.setOnGroupClickListener(this);
        this.expandableListView.setOnChildClickListener(this);
    }

    public void setBaseEntityExpandableListAdapter(BaseEntityExpandableListAdapter<T1, T2> baseEntityExpandableListAdapter) {
        this.baseEntityExpandableListAdapter = baseEntityExpandableListAdapter;
        if(expandableListView != null){
            expandableListView.setAdapter(baseEntityExpandableListAdapter);
        }
    }

    public ExpandableListView getExpandableListView() {
        return expandableListView;
    }

    public BaseEntityExpandableListAdapter<T1, T2> getBaseEntityExpandableListAdapter() {
        return baseEntityExpandableListAdapter;
    }

    public void setOnEntityExpandableListClickListener(OnEntityExpandableListClickListener<T1, T2> onEntityExpandableListClickListener){
        this.onEntityExpandableListClickListener = onEntityExpandableListClickListener;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if(onEntityExpandableListClickListener != null){
            return onEntityExpandableListClickListener.onGroupClick(
                    parent.getContext(), expandableListView, v, (T1) getBaseEntityExpandableListAdapter().getGroup(groupPosition), groupPosition);
        }
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if(onEntityExpandableListClickListener != null){
            return onEntityExpandableListClickListener.onChildClick(parent.getContext(), expandableListView,v,
                    (T1)getBaseEntityExpandableListAdapter().getGroup(groupPosition),
                    (T2)getBaseEntityExpandableListAdapter().getChild(groupPosition, childPosition),
                    groupPosition, childPosition);
        }
        return false;
    }
}
