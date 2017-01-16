package com.wcl.easybaseadapter.expandablelistView.listener;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

public interface OnEntityExpandableListClickListener<T1, T2>{
	/**
	 * 组视图点击事件
	 * @param context
	 * @param expandListView
	 * @param groupView
	 * @param groupEntity
	 * @param groupPosition
	 */
	public boolean onGroupClick(Context context, ExpandableListView expandListView, View groupView, T1 groupEntity, int groupPosition);
	
	/**
	 * 子视图点击事件
	 * @param context
	 * @param expandListView
	 * @param childView
	 * @param groupEntity
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 */
	public boolean onChildClick(Context context, ExpandableListView expandListView, View childView, T1 groupEntity, T2 childEntity, int groupPosition, int childPosition);
}
