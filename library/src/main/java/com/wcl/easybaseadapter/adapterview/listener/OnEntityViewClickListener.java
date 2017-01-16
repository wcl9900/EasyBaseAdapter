package com.wcl.easybaseadapter.adapterview.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * 通用适配器子视图点击监听器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public interface OnEntityViewClickListener<T>{
	/**
	 * 通用适配器子视图点击监听函数
	 * @param clickView
	 * @param entity
	 * @param position
	 */
	public void onEntityViewClick(AdapterView<?> parent, View clickView, T entity, int position);
}