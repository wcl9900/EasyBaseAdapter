package com.wcl.easybaseadapter.adapterview.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * 通用适配器子视图长按子项监听器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public interface OnEntityViewLongClickListener<T> {
	/**
	 * 通用适配器子视图长按监听函数
	 * @param clickView
	 * @param entity
	 * @param position
	 */
	public void onEntityViewLongClick(AdapterView<?> parent, View clickView, T entity, int position, long id);
}
