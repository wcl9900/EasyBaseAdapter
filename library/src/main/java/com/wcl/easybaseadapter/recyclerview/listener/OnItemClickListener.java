package com.wcl.easybaseadapter.recyclerview.listener;

import android.view.View;

import com.wcl.easybaseadapter.recyclerview.RecyclerViewHolder;

/**
 * 通用适配器子视图点击监听器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public interface OnItemClickListener<T>{
	/**
	 * 通用适配器子视图点击监听函数
	 * @param clickView
	 * @param entity
	 * @param position
	 */
	void onItemViewClick(View clickView, RecyclerViewHolder viewHolder, T entity, int position);
}