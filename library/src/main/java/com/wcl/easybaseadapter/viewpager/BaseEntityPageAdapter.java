package com.wcl.easybaseadapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.constant.AdapterConstant;
import com.wcl.easybaseadapter.viewholder.EntityViewHolder;
import com.wcl.easybaseadapter.viewpager.listener.OnEntityViewPagerClickListener;

import java.util.List;

/**
 * 基于ViewPager的泛型实体数据适配器
 * @author 王春龙
 *
 * @param <T>
 */
public class BaseEntityPageAdapter<T> extends PagerAdapter implements OnClickListener {
	
	protected List<T> entityList;
	
	protected Context context;
	
	protected BaseAdapterEntityViewManage<T> adapterItemManage;
	
	private OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener;
	
	/**
	 * 
	 * @param context
	 * @param entityList 数据实体列表
	 * @param adapterItemManage 生成子视图的管理器
	 */
	public BaseEntityPageAdapter(Context context, List<T> entityList, BaseAdapterEntityViewManage<T> adapterItemManage){
		this.context = context;
		this.entityList = entityList;
		this.adapterItemManage = adapterItemManage;
	}
	
	public void setOnEntityViewPagerClickListener(OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener){
		this.onEntityViewPagerClickListener = onEntityViewPagerClickListener;
	}
	
	@Override
	public int getCount() {
		return entityList.size();
	}

	public Object getItem(int position){
		return entityList.get(position);
	}
	
	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		T entity = entityList.get(position);
		View view = adapterItemManage.getAdapterItemView(context, entity, position);
		container.addView(view);
		EntityViewHolder<T> holder = new EntityViewHolder<T>();
		holder.itemView = view;
		holder.position = position;
		holder.entity = entity;
		holder.viewGroup = container;
		view.setTag(AdapterConstant.TAG_KEY, holder);
		view.setClickable(true);
		view.setOnClickListener(this);
		adapterItemManage.updateAdapterItemView(context, holder, entity, position);
		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = (View) object;
		 container.removeView(view);
	}

	@Override
	public void onClick(View v) {
		@SuppressWarnings("unchecked")
		EntityViewHolder<T> viewHolder = (EntityViewHolder<T>) v.getTag(AdapterConstant.TAG_KEY);
		if(onEntityViewPagerClickListener != null){
			onEntityViewPagerClickListener.onEntityViewClick((ViewPager)viewHolder.viewGroup, v, viewHolder.entity, viewHolder.position);
		}
	}
}
