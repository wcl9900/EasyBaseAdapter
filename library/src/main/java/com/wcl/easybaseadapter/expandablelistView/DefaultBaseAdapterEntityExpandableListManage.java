package com.wcl.easybaseadapter.expandablelistView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * 默认的伸缩列表适配器视图管理器抽象类
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public abstract class DefaultBaseAdapterEntityExpandableListManage<T1, T2> implements
		BaseAdapterEntityExpandableListManage<T1, T2> {

	private int resIdChild;
	private int resIdGroup;
	
	public DefaultBaseAdapterEntityExpandableListManage(int resIdGroup, int resIdChild) {
		super();
		this.resIdGroup = resIdGroup;
		this.resIdChild = resIdChild;
	}

	@Override
	public View getGroupView(Context context,
							 ExpandableListView expandListView, boolean isExpanded,
							 T1 groupEntity, int groupPosition) {
		return LayoutInflater.from(context).inflate(resIdGroup, null);
	}

	@Override
	public View getChildView(Context context,
							 ExpandableListView expandListView, T2 childEntity,
							 int groupPosition, int childPosition) {
		return LayoutInflater.from(context).inflate(resIdChild, null);
	}
	
}
