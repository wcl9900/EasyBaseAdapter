package com.wcl.easybaseadapter.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.wcl.easybaseadapter.viewpager.listener.OnEntityViewPagerClickListener;

/**
 * ViewPager 视图包装类。可用于对ViewPager进行基于{@link BaseEntityPageAdapter}适配器的数据绑定,可以使用实体数据监听器{@link OnEntityViewPagerClickListener}等操作。
 * @author 王春龙
 *
 * @param <T>
 */
public class ViewPagerEntityWrapper<T>{
	
	private ViewPager viewPager;

	private OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener;
	
	public ViewPagerEntityWrapper(ViewPager viewPager){
		this.viewPager = viewPager;
	}
	
	public ViewPager getViewPager(){
		return this.viewPager;
	}
	
	@SuppressWarnings("unchecked")
	public void setAdapter(PagerAdapter adapter){
		this.viewPager.setAdapter(adapter);
		if(adapter != null && adapter instanceof BaseEntityPageAdapter){
			((BaseEntityPageAdapter<T>)adapter).setOnEntityViewPagerClickListener(onEntityViewPagerClickListener);
		}
	}

	
	@SuppressWarnings("unchecked")
	public void setOnEntityViewPagerClickListener(OnEntityViewPagerClickListener<T> onEntityViewPagerClickListener){
		this.onEntityViewPagerClickListener = onEntityViewPagerClickListener;
		if(viewPager != null && viewPager.getAdapter() != null && viewPager.getAdapter() instanceof BaseEntityPageAdapter){
			((BaseEntityPageAdapter<T>)viewPager.getAdapter()).setOnEntityViewPagerClickListener(onEntityViewPagerClickListener);
		}
	}
}
