package com.wcl.easybaseadapter.adapterview;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import com.wcl.easybaseadapter.constant.AdapterConstant;
import com.wcl.easybaseadapter.adapterview.listener.OnEntityViewClickListener;
import com.wcl.easybaseadapter.adapterview.listener.OnEntityViewLongClickListener;

/**
 * AdapterView 列表视图包装类。可用于对AdapterView进行基于{@link BaseEntityViewAdapter}
 * 适配器的数据绑定,可以使用实体数据监听器
 * {@link OnEntityViewClickListener}等操作。
 * 
 * @author 王春龙
 *
 */
public class AdapterViewEntityWrapper<T> implements OnItemClickListener,
		OnItemSelectedListener, OnItemLongClickListener {

	private AdapterView<?> adapterView;

	@SuppressWarnings("rawtypes")
	private OnEntityViewClickListener onEntityViewClickListener;

	@SuppressWarnings("rawtypes")
	private OnEntityViewLongClickListener onEntityViewLongClickListener;

	private OnItemClickListener itemClickListener;

	private OnItemSelectedListener itemSelectedListener;

	@SuppressWarnings("rawtypes")
	public AdapterViewEntityWrapper(AdapterView adapterView) {
		this.adapterView = adapterView;
		init();
	}

	private void init() {
		if (adapterView instanceof Spinner) {
			adapterView.setOnItemSelectedListener(this);
		}else {
			adapterView.setOnItemClickListener(this);
			adapterView.setOnItemSelectedListener(this);
			adapterView.setOnItemLongClickListener(this);
		}
	}

	@SuppressWarnings("rawtypes")
	public AdapterView getAdapterView() {
		return this.adapterView;
	}

	@SuppressWarnings("unchecked")
	public void setAdapter(Adapter adapter) {
		if (getAdapterView() != null) {
			getAdapterView().setAdapter(adapter);
		}
	}


	/**
	 * 设定Item选中项
	 * 
	 * @param itemPosition
	 */
	public void setSelectItem(int itemPosition) {
		if (adapterView.getAdapter() != null
				&& adapterView.getAdapter() instanceof ItemSelectAdapter<?>) {
			ItemSelectAdapter<?> adapter = ((ItemSelectAdapter<?>) adapterView
					.getAdapter());
			if (!adapter.isClickRepeatEnable()
					&& itemPosition == adapter.getSelectItemPosition())
				return;
			((ItemSelectAdapter<?>) adapterView.getAdapter())
					.setSelectItemPosition(itemPosition);
		}
		adapterView.setSelection(itemPosition);
	}

	/**
	 * 获取Item选中位置
	 * 
	 * @return
	 */
	public int getSelectedItemPosition() {
		Adapter adapter = getAdapter();
		if (adapter != null && adapter instanceof ItemSelectAdapter<?>) {
			return ((ItemSelectAdapter<?>) adapter).getSelectItemPosition();
		} else {
			return adapterView.getSelectedItemPosition();
		}
	}

	private Adapter getAdapter() {
		Adapter adapter = null;
		if (adapterView.getAdapter() != null) {
			if (adapterView.getAdapter() instanceof BaseAdapter) {
				adapter = adapterView.getAdapter();
			} else if (adapterView.getAdapter() instanceof android.widget.HeaderViewListAdapter) {
				adapter = ((android.widget.HeaderViewListAdapter) adapterView
						.getAdapter()).getWrappedAdapter();
			}
		}
		return adapter;
	}

	public void setOnItemClickListener(
			android.widget.AdapterView.OnItemClickListener listener) {
		this.itemClickListener = listener;
	}

	public void setOnItemSelectedListener(
			OnItemSelectedListener onItemSelectedListener) {
		this.itemSelectedListener = onItemSelectedListener;
	}

	/**
	 * 设定数据实体视图点击监听器
	 * 
	 * @param listener
	 */
	public void setOnEntityViewClickListener(
			OnEntityViewClickListener<T> listener) {
		this.onEntityViewClickListener = listener;
	}

	/**
	 * 设定数据实体视图长按点击监听器
	 * 
	 * @param listener
	 */
	public void setOnEntityViewLongClickListener(
			OnEntityViewLongClickListener<T> listener) {
		this.onEntityViewLongClickListener = listener;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		Adapter adapter = getAdapter();
		BaseEntityViewAdapter.EntityViewHolder holder = null;

		if (view.getTag(AdapterConstant.TAG_KEY) != null
				&& view.getTag(AdapterConstant.TAG_KEY) instanceof BaseEntityViewAdapter.EntityViewHolder) {
			holder = (BaseEntityViewAdapter.EntityViewHolder) view.getTag(AdapterConstant.TAG_KEY);
		}

		if (adapter != null && adapter instanceof ItemSelectAdapter) {
			ItemSelectAdapter<T> itemAdapter = ((ItemSelectAdapter<T>) adapter);
			if (holder != null) {
				if (!itemAdapter.isClickRepeatEnable()
						&& holder.position == itemAdapter
								.getSelectItemPosition())
					return;
				itemAdapter.setSelectItemPosition(holder.position);
			}
		}

		if (itemClickListener != null) {
			if (parent.getAdapter() instanceof android.widget.HeaderViewListAdapter) {
				position = position
						- ((android.widget.HeaderViewListAdapter) parent
								.getAdapter()).getHeadersCount();
			}
			itemClickListener.onItemClick(parent, view, position, id);
		}

		if (onEntityViewClickListener != null && holder != null) {
			onEntityViewClickListener.onEntityViewClick(parent, view,
					holder.entity, holder.position);
		}
	}

	public void notifyDataSetChanged() {
		Adapter adpter = getAdapter();
		if (adpter != null && adpter instanceof BaseAdapter) {
			((BaseAdapter) adpter).notifyDataSetChanged();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
							   long id) {
		if (itemSelectedListener != null) {
			itemSelectedListener.onItemSelected(parent, view, position, id);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		if (itemSelectedListener != null) {
			itemSelectedListener.onNothingSelected(parent);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
								   int position, long id) {
		if (onEntityViewLongClickListener != null
				&& view.getTag(AdapterConstant.TAG_KEY) != null
				&& view.getTag(AdapterConstant.TAG_KEY) instanceof BaseEntityViewAdapter.EntityViewHolder) {
			BaseEntityViewAdapter.EntityViewHolder<?> holder = (BaseEntityViewAdapter.EntityViewHolder<?>) view
					.getTag(AdapterConstant.TAG_KEY);
			onEntityViewLongClickListener.onEntityViewLongClick(parent, view,
					holder.entity, holder.position, id);
			return true;
		}
		return false;
	}
}
