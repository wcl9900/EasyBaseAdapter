package com.wcl.easybaseadapter.expandablelistView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.wcl.easybaseadapter.constant.AdapterConstant;

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
		
		ViewHolderGroup viewHolderGroup = null;
		if(convertView == null){
			convertView = viewManage.getGroupView(expandListView.getContext(), expandListView,isExpanded, groupEntityList.get(groupPosition), groupPosition);
			viewHolderGroup = new ViewHolderGroup();
			convertView.setTag(AdapterConstant.TAG_KEY, viewHolderGroup);
		}
		
		viewHolderGroup = (ViewHolderGroup) convertView.getTag(AdapterConstant.TAG_KEY);		
		viewHolderGroup.groupPosition = groupPosition;
		viewHolderGroup.groupEntity = groupEntityList.get(groupPosition);
		
		viewManage.updateGroupView(expandListView.getContext(), expandListView,isExpanded, convertView, groupEntityList.get(groupPosition), groupPosition);

		return convertView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolderChild viewHolderChild = null;
		T1 groupEntity = groupEntityList.get(groupPosition);
		T2 childEntity = childEntityList.get(groupPosition).get(childPosition);
		if(convertView == null){
			convertView = viewManage.getChildView(expandListView.getContext(), expandListView, childEntity, groupPosition, childPosition);
			viewHolderChild = new ViewHolderChild();
			convertView.setTag(AdapterConstant.TAG_KEY, viewHolderChild);
		}
		
		viewHolderChild = (ViewHolderChild) convertView.getTag(AdapterConstant.TAG_KEY);	
		viewHolderChild.groupPosition = groupPosition;
		viewHolderChild.childPosition = childPosition;
		viewHolderChild.childEntity = childEntity;
		viewHolderChild.groupEntity = groupEntity;
		
		viewManage.updateChildView(expandListView.getContext(), expandListView, convertView, childEntity, groupPosition, childPosition);
	
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public class ViewHolderGroup{
		public int groupPosition;
		public T1 groupEntity;
	}
	
	public class ViewHolderChild{
		public int groupPosition;
		public int childPosition;
		public T1 groupEntity;
		public T2 childEntity;
	}
}
