package com.wcl.easybaseadapter.expandablelistView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.wcl.easybaseadapter.expandablelistView.listener.OnExpandableListItemViewDecorateListener;

import java.util.List;

/**
 * 带有选中功能的伸展列表适配器
 * @author 王春龙
 *
 * @param <T1> 组视图数据实体
 * @param <T2> 子视图数据实体
 */
public class BaseEntitySelectedExpandableListAdapter<T1, T2> extends
		BaseEntityExpandableListAdapter<T1, T2> {
	
	private int selectedGroud = -1;
	private int selectedChild = -1;
	
	private int[] selectGroupDrawable;
	private int[] selectChildDrawable;
	
	private OnExpandableListItemViewDecorateListener decorateListener;
	
	private boolean singleGroupExpandEnable = false;
	
	public BaseEntitySelectedExpandableListAdapter(ExpandableListView expandListView,
												   List<T1> groupEntityList, List<List<T2>> childEntityList,
												   BaseAdapterEntityExpandableListManage<T1, T2> viewManage,
												   int[] selectGroupDrawable, int[] selectChildDrawable) {
		this(expandListView, groupEntityList, childEntityList, viewManage, null, selectGroupDrawable, selectChildDrawable);
	}

	public BaseEntitySelectedExpandableListAdapter(ExpandableListView expandListView,
												   List<T1> groupEntityList, List<List<T2>> childEntityList,
												   BaseAdapterEntityExpandableListManage<T1, T2> viewManage, OnExpandableListItemViewDecorateListener decorateListener,
												   int[] selectGroupDrawable, int[] selectChildDrawable) {
		super(expandListView, groupEntityList, childEntityList, viewManage);

		this.decorateListener = decorateListener;
		
		this.selectGroupDrawable = selectGroupDrawable;
		this.selectChildDrawable = selectChildDrawable;
	}
	
	/**
	 * 选中组视图
	 * @param group
	 * @return
	 */
	public boolean setSelectedGroup(int group){
		return setSelected(group, -1);
	}
	
	/**
	 * 选中子视图
	 * @param group
	 * @param child
	 * @return
	 */
	public boolean setSelectedChild(int group, int child){
		return setSelected(group, child);
	}
	
	private boolean setSelected(int group, int child){
		if(this.selectedGroud == group && this.selectedChild == child){
			return false;
		}
		this.selectedGroud = group;
		this.selectedChild = child;
		this.notifyDataSetChanged();
		return true;
	}
	
	/**
	 * 设定列表只展开单组数据
	 * @param enable
	 */
	public void setSingleGroupExpandEnable(boolean enable){
		this.singleGroupExpandEnable = enable;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		convertView = super.getGroupView(groupPosition, isExpanded, convertView, parent);

		if(selectGroupDrawable == null || selectGroupDrawable.length < 2) return convertView;
		
		if(selectedGroud == groupPosition && selectedChild == -1){
			if(convertView instanceof ImageView){
				((ImageView)convertView).setImageResource(selectGroupDrawable[0]);
			}
			else{
				convertView.setBackgroundResource(selectGroupDrawable[0]);
			}
			if(decorateListener != null){
				decorateListener.OnGroupItemViewSelected(convertView);
			}
		}
		else{
			if(singleGroupExpandEnable){
				if(selectedGroud != groupPosition && isExpanded){
					expandListView.collapseGroup(groupPosition);
				}
			}
			
			if(convertView instanceof ImageView){
				((ImageView)convertView).setImageResource(selectGroupDrawable[1]);
			}
			else{
				convertView.setBackgroundResource(selectGroupDrawable[1]);
			}
			
			if(decorateListener != null){
				decorateListener.OnGroupItemViewDefault(convertView);
			}
		}
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = super.getChildView(groupPosition, childPosition, isLastChild,
				convertView, parent);

		if(selectChildDrawable == null || selectChildDrawable.length < 2) return convertView;
		
		if(selectedGroud == groupPosition && selectedChild == childPosition){
			if(convertView instanceof ImageView){
				((ImageView)convertView).setImageResource(selectChildDrawable[0]);
			}
			else{
				convertView.setBackgroundResource(selectChildDrawable[0]);
			}
			if(decorateListener != null){
				decorateListener.OnChildItemViewSelected(convertView);
			}
		}
		else{
			if(convertView instanceof ImageView){
				((ImageView)convertView).setImageResource(selectChildDrawable[1]);
			}
			else{
				convertView.setBackgroundResource(selectChildDrawable[1]);
			}
			if(decorateListener != null){
				decorateListener.OnChildItemViewDefault(convertView);
			}
		}
		return convertView;
	}
}
