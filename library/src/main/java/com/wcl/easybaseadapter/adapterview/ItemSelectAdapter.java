package com.wcl.easybaseadapter.adapterview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.adapterview.listener.OnItemViewDecorateListener;

import java.util.List;

/**
 * 带有选中Item功能的数据适配器
 * 
 * @author 王春龙
 * 
 * @param <T>
 *            数据实体
 */
public class ItemSelectAdapter<T> extends BaseEntityViewAdapter<T> {

	private int selectPosition = -1;
	private int selectedDrawable = -1;
	private int selectorDrawable = -1;

	private boolean clickRepeatEnable = false;

	private OnItemViewDecorateListener decorateListener;

	public ItemSelectAdapter(Context context, List<T> entityList,
							 BaseAdapterEntityViewManage<T> adapterItemManage,
							 int selectedDrawable, int selectorDrawable) {
		this(context, entityList, adapterItemManage, null, selectedDrawable,
				selectorDrawable);
	}

	public ItemSelectAdapter(Context context, List<T> entityList,
							 BaseAdapterEntityViewManage<T> adapterItemManage,
							 OnItemViewDecorateListener onItemViewDecorateListener,
							 int selectedDrawable, int selectorDrawable) {
		super(context, entityList, adapterItemManage);
		this.decorateListener = onItemViewDecorateListener;
		this.selectedDrawable = selectedDrawable;
		this.selectorDrawable = selectorDrawable;
	}

	public void setOnItemViewDecorateListener(
			OnItemViewDecorateListener listener) {
		this.decorateListener = listener;
	}

	/**
	 * 对已点击item是否可以重复点击触发事件
	 * 
	 * @param enable
	 */
	public void setClickRepeatEnable(boolean enable) {
		this.clickRepeatEnable = enable;
	}

	/**
	 * 是否对已点击item可以重复点击触发事件
	 * 
	 * @return
	 */
	public boolean isClickRepeatEnable() {
		return clickRepeatEnable;
	}

	/**
	 * 设定选择项
	 * 
	 * @param selectPosition
	 */
	public void setSelectItemPosition(int selectPosition) {
		this.selectPosition = selectPosition;
		this.notifyDataSetChanged();
	}

	/**
	 * 获取选择的子项位置
	 * 
	 * @return
	 */
	public int getSelectItemPosition() {
		return this.selectPosition;
	}

	/**
	 * 获取当前已选择的Item子项的实体数据
	 * 
	 * @return
	 */
	public T getSelectedItem() {
		if (selectPosition >= 0 && entityList != null
				&& selectPosition < entityList.size()) {
			return entityList.get(selectPosition);
		} else {
			return null;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		if (selectPosition == position) {
			if (selectedDrawable != -1) {
				if (view instanceof ImageView) {
					((ImageView) view).setImageResource(selectedDrawable);
				} else {
					view.setBackgroundResource(selectedDrawable);
				}
			}

			if (decorateListener != null) {
				decorateListener.OnItemViewSelected(view);
			}
		} else {
			if (selectorDrawable != -1) {
				if (view instanceof ImageView) {
					((ImageView) view).setImageResource(selectorDrawable);
				} else {
					view.setBackgroundResource(selectorDrawable);
				}
			}
			if (decorateListener != null) {
				decorateListener.OnItemViewDefault(view);
			}
		}

		return view;
	}
}
