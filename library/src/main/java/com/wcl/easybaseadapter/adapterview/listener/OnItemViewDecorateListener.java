package com.wcl.easybaseadapter.adapterview.listener;

import android.view.View;

/**
 * 列表Item视图装饰器监听器
 * @author 王春龙
 *
 */
public interface OnItemViewDecorateListener {
	/**
	 * Item选中状态装饰
	 * @param view
	 */
	public void OnItemViewSelected(View view);
	/**
	 * Item默认状态装饰
	 * @param view
	 */
	public void OnItemViewDefault(View view);
}
