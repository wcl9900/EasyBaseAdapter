package com.wcl.easybaseadapter.expandablelistView;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.wcl.easybaseadapter.viewholder.ViewChildHolder;
import com.wcl.easybaseadapter.viewholder.ViewGroupHolder;

/**
 * 伸缩树列表视图管理器
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public interface BaseAdapterEntityExpandableListManage<T1, T2> {
	/**
	 * 获取组视图
	 * @param context
	 * @param expandListView
	 * @param isExpanded
	 * @param groupEntity
	 * @param groupPosition
	 * @return
	 */
	View getGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, T1 groupEntity, int groupPosition);
	/**
	 * 更新组视图
	 * @param context
	 * @param expandListView
	 * @param isExpanded
	 * @param groupEntity
	 * @param groupPosition
	 */
	void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, ViewGroupHolder<T1> viewGroupHolder, T1 groupEntity, int groupPosition);
	
	/**
	 * 获取子视图
	 * @param context
	 * @param expandListView
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 * @return
	 */
	View getChildView(Context context, ExpandableListView expandListView, T2 childEntity, int groupPosition, int childPosition);
	/**
	 * 更新子视图
	 * @param context
	 * @param expandListView
	 * @param childEntity
	 * @param groupPosition
	 * @param childPosition
	 */
	void updateChildView(Context context, ExpandableListView expandListView, ViewChildHolder<T1, T2> viewChildHolder, T2 childEntity, int groupPosition, int childPosition);
}
