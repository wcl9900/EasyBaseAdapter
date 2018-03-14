package com.wcl.easybaseadapter.entityviewmanage;

import android.content.Context;
import android.view.View;

import com.wcl.easybaseadapter.adapterview.DefaultBaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.viewholder.EntityViewHolder;

/**
 * 适配器子视图管理器，用来管理子视图的生成和更新。可以使用默认的视图管理器类{@link DefaultBaseAdapterEntityViewManage}完成对适配器视图的管理
 * @author 王春龙
 *
 * @param <T> 子视图所需实体数据
 */
public interface BaseAdapterEntityViewManage<T> {
	/**
	 * 获取子视图
	 * @param context
	 * @param entity 数据实体
	 * @param position 子视图所在位置
	 * @return
	 */
	View getAdapterItemView(Context context, T entity, int position);
	/**
	 * 更新子视图
	 * @param context
	 * @param holder 需要更新的子视图
	 * @param entity 更新子视图所需实体数据
	 * @param position 更新子视图所在位置
	 */
	void updateAdapterItemView(Context context, EntityViewHolder<T> holder, T entity, int position);
}
