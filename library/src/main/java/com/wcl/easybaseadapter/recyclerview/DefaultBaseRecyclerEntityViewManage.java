package com.wcl.easybaseadapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 默认的抽象适配器视图Item管理器
 * @author 王春龙
 *
 * @param <T> 子视图所需实体数据
 */
public abstract class DefaultBaseRecyclerEntityViewManage<T> implements
		BaseRecyclerEntityViewManage<T> {

	private int resId;
	
	public DefaultBaseRecyclerEntityViewManage(int resId) {
		this.resId = resId;
	}
	
	@Override
	public View getAdapterItemView(Context context) {
		return LayoutInflater.from(context).inflate(resId, null);
	}

	@Override
	public void updateAdapterItemView(Context context, RecyclerViewHolder holder, T entity, int position) {
		updateItemView(context, holder, entity, position);
	}

	/**
	 * 更新子视图
	 * @param context
	 * @param holder
	 * @param entity
	 * @param position
	 */
	abstract public void updateItemView(Context context, RecyclerViewHolder holder,
										T entity, int position) ;
}
