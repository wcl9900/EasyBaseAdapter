package com.wcl.easybaseadapter.viewholder;

/**
 * 伸缩列表子视图缓存器
 * @param <T1> 组视图数据实体类型
 * @param <T2> 子视图数据实体类型
 */
public class ViewChildHolder<T1, T2> extends BaseEntityViewHolder{
	public int groupPosition;
	public int childPosition;
	public T1 groupEntity;
	public T2 childEntity;
}