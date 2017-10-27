package com.wcl.easybaseadapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wcl.easybaseadapter.recyclerview.listener.OnItemClickListener;
import com.wcl.easybaseadapter.recyclerview.listener.OnItemLongClickListener;

import java.util.List;

/**
 * {@link RecyclerView}的{@link android.support.v7.widget.RecyclerView.Adapter}的封装类
 * Created by wangchunlong on 2017/10/27.
 */

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter{

    protected List<T> entityList;

    protected Context context;

    protected BaseRecyclerEntityViewManage<T> baseRecyclerEntityViewManage;

    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    public BaseRecyclerViewAdapter(List<T> entityList, Context context, BaseRecyclerEntityViewManage<T> baseRecyclerEntityViewManage) {
        this.entityList = entityList;
        this.context = context;
        this.baseRecyclerEntityViewManage = baseRecyclerEntityViewManage;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = baseRecyclerEntityViewManage.getAdapterItemView(context);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(itemView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemViewClick(holder.itemView, (RecyclerViewHolder) holder, entityList.get(position), position);
                }
            });
        }
        if(onItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClickListener.onItemLongClick(holder.itemView, (RecyclerViewHolder) holder, entityList.get(position), position);
                }
            });
        }
        baseRecyclerEntityViewManage.updateAdapterItemView(context, (RecyclerViewHolder)holder, entityList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }
}
