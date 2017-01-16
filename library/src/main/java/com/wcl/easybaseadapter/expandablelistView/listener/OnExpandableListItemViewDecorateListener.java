package com.wcl.easybaseadapter.expandablelistView.listener;

import android.view.View;

/**
 * 树视图item选项状态装饰器
 * @author 王春龙
 *
 */
public interface OnExpandableListItemViewDecorateListener {
	/**
	 * group Item选中状态装饰
	 * @param view
	 */
	void OnGroupItemViewSelected(View view);
	/**
	 * group Item默认状态装饰
	 * @param view
	 */
	void OnGroupItemViewDefault(View view);
	/**
	 * child Item选中状态装饰
	 * @param view
	 */
	void OnChildItemViewSelected(View view);
	/**
	 * child Item默认状态装饰
	 * @param view
	 */
	void OnChildItemViewDefault(View view);
}
