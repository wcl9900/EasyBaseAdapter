package com.wcl.easybaseadapter.viewpager.listener;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 通用ViewPager适配器子视图点击监听器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public interface OnEntityViewPagerClickListener<T>{
	/**
	 * 通用ViewPager适配器子视图点击监听函数
	 * @param clickView
	 * @param entity
	 * @param position
	 */
	public void onEntityViewClick(ViewPager parent, View clickView, T entity, int position);
}
