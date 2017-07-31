package com.wcl.easybaseadapter.expandablelistView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.wcl.easybaseadapter.constant.AdapterConstant;
import com.wcl.easybaseadapter.viewholder.ViewChildHolder;
import com.wcl.easybaseadapter.viewholder.ViewGroupHolder;

import java.util.List;

/**
 * 树结构视图适配器
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public class BaseEntityExpandableListAdapter<T1, T2> extends BaseExpandableListAdapter {

	private List<T1> groupEntityList;
	private List<List<T2>> childEntityList;
	
	private BaseAdapterEntityExpandableListManage<T1, T2> viewManage;
	
	protected ExpandableListView expandListView;
	
	public BaseEntityExpandableListAdapter(ExpandableListView expandListView, List<T1> groupEntityList, List<List<T2>> childEntityList,
										   BaseAdapterEntityExpandableListManage<T1, T2> viewManage) {
		this.expandListView = expandListView;
		
		this.groupEntityList = groupEntityList;
		this.childEntityList = childEntityList;
		
		this.viewManage = viewManage;
	}

	@Override
	public int getGroupCount() {
		return groupEntityList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition < childEntityList.size() && groupPosition >= 0){
			return childEntityList.get(groupPosition).size();
		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupEntityList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childEntityList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		if(groupPosition <0 || groupPosition >= getGroupCount()) return convertView;
		
		ViewGroupHolder<T1> viewGroupHolder;
		if(convertView == null){
			convertView = viewManage.getGroupView(expandListView.getContext(), expandListView,isExpanded, groupEntityList.get(groupPosition), groupPosition);
			viewGroupHolder = new ViewGroupHolder<T1>();
			convertView.setTag(AdapterConstant.TAG_KEY, viewGroupHolder);
		}
		
		viewGroupHolder = (ViewGroupHolder) convertView.getTag(AdapterConstant.TAG_KEY);
		viewGroupHolder.groupPosition = groupPosition;
		viewGroupHolder.groupEntity = groupEntityList.get(groupPosition);
		viewGroupHolder.itemView = convertView;
		viewGroupHolder.viewGroup = parent;
		
		viewManage.updateGroupView(expandListView.getContext(), expandListView,isExpanded, viewGroupHolder, viewGroupHolder.groupEntity, groupPosition);

		return convertView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		ViewChildHolder<T1,T2> viewChildHolder;
		T1 groupEntity = groupEntityList.get(groupPosition);
		T2 childEntity = childEntityList.get(groupPosition).get(childPosition);
		if(convertView == null){
			convertView = viewManage.getChildView(expandListView.getContext(), expandListView, childEntity, groupPosition, childPosition);
			viewChildHolder = new ViewChildHolder();
			convertView.setTag(AdapterConstant.TAG_KEY, viewChildHolder);
		}
		
		viewChildHolder = (ViewChildHolder) convertView.getTag(AdapterConstant.TAG_KEY);
		viewChildHolder.groupPosition = groupPosition;
		viewChildHolder.childPosition = childPosition;
		viewChildHolder.childEntity = childEntity;
		viewChildHolder.groupEntity = groupEntity;
		viewChildHolder.itemView = convertView;
		viewChildHolder.viewGroup = parent;
		
		viewManage.updateChildView(expandListView.getContext(), expandListView, viewChildHolder, childEntity, groupPosition, childPosition);
	
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
