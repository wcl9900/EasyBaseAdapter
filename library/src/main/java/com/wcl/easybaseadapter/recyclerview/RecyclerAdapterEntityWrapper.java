package com.wcl.easybaseadapter.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.wcl.easybaseadapter.recyclerview.listener.OnItemClickListener;
import com.wcl.easybaseadapter.recyclerview.listener.OnItemLongClickListener;

/**
 * {@link RecyclerView}的装饰器，可用来设定适配器和item点击回调监听接口
 * Created by wangchunlong on 2017/10/27.
 */

public class RecyclerAdapterEntityWrapper<T> {

    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter<T> baseRecyclerViewAdapter;

    @SuppressWarnings("rawtypes")
    private OnItemClickListener<T> onItemClickListener;

    @SuppressWarnings("rawtypes")
    private OnItemLongClickListener<T> onItemLongClickListener;

    public RecyclerAdapterEntityWrapper(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setAdapter(BaseRecyclerViewAdapter<T> baseRecyclerViewAdapter){
        recyclerView.setAdapter(baseRecyclerViewAdapter);
        this.baseRecyclerViewAdapter = baseRecyclerViewAdapter;
        if(onItemClickListener != null){
            baseRecyclerViewAdapter.setOnItemClickListener(onItemClickListener);
        }
        if(onItemLongClickListener != null){
            baseRecyclerViewAdapter.setOnItemLongClickListener(onItemLongClickListener);
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int index) {
        recyclerView.addItemDecoration(itemDecoration, index);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        if(baseRecyclerViewAdapter != null){
            baseRecyclerViewAdapter.setOnItemClickListener(onItemClickListener);
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
        if(baseRecyclerViewAdapter != null){
            baseRecyclerViewAdapter.setOnItemLongClickListener(onItemLongClickListener);
        }
    }
}
