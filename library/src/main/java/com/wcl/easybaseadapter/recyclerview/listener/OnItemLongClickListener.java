package com.wcl.easybaseadapter.recyclerview.listener;

import android.view.View;

import com.wcl.easybaseadapter.recyclerview.RecyclerViewHolder;

/**
 * 通用适配器子视图长按子项监听器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public interface OnItemLongClickListener<T> {
	/**
	 * 通用适配器子视图长按监听函数
	 * @param clickView
	 * @param entity
	 * @param position
	 */
	boolean onItemLongClick(View clickView, RecyclerViewHolder viewHolder, T entity, int position);
}
