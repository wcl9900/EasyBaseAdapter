package com.wcl.easybaseadapter.adapterview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wcl.easybaseadapter.constant.AdapterConstant;
import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.viewholder.EntityViewHolder;

import java.util.List;

/**
 * 通用适配器
 * @author 王春龙
 *
 * @param <T>数据实体类型
 */
public class BaseEntityViewAdapter<T> extends BaseAdapter {
	
	protected List<T> entityList;
	
	protected Context context;
	
	protected BaseAdapterEntityViewManage<T> adapterItemManage;
	
	/**
	 * 
	 * @param context
	 * @param entityList 数据实体列表
	 * @param adapterItemManage 生成子视图的管理器
	 */
	public BaseEntityViewAdapter(Context context, List<T> entityList, BaseAdapterEntityViewManage<T> adapterItemManage){
		this.context = context;
		this.entityList = entityList;
		this.adapterItemManage = adapterItemManage;
	}
	
	@Override
	public int getCount() {
		return entityList.size();
	}

	@Override
	public Object getItem(int position) {
		return entityList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EntityViewHolder<T> holder;
		if(convertView == null){
			convertView = adapterItemManage.getAdapterItemView(context, entityList.get(position), position);
			holder = new EntityViewHolder<T>();
			convertView.setTag(AdapterConstant.TAG_KEY, holder);
		}
		
		holder = (EntityViewHolder<T>) convertView.getTag(AdapterConstant.TAG_KEY);
		holder.entity = entityList.get(position);
		holder.position = position;
		holder.itemView = convertView;
		adapterItemManage.updateAdapterItemView(context, holder, holder.entity, position);

		return convertView;
	}
}
