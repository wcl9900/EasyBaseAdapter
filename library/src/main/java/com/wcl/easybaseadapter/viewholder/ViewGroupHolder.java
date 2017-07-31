package com.wcl.easybaseadapter.viewholder;

/**
 * 伸缩列表组视图缓存器
 * @param <T1> 组视图数据实体类型
 */
public class ViewGroupHolder<T1> extends BaseEntityViewHolder{
	public int groupPosition;
	public T1 groupEntity;
}