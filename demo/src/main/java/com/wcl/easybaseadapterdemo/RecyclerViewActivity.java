package com.wcl.easybaseadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wcl.easybaseadapter.recyclerview.BaseRecyclerEntityViewManage;
import com.wcl.easybaseadapter.recyclerview.BaseRecyclerViewAdapter;
import com.wcl.easybaseadapter.recyclerview.DefaultBaseRecyclerEntityViewManage;
import com.wcl.easybaseadapter.recyclerview.listener.OnItemClickListener;
import com.wcl.easybaseadapter.recyclerview.listener.OnItemLongClickListener;
import com.wcl.easybaseadapter.recyclerview.RecyclerAdapterEntityWrapper;
import com.wcl.easybaseadapter.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            dataList.add("item:"+i);
        }

        RecyclerAdapterEntityWrapper<String> recyclerAdapterEntityWrapper = new RecyclerAdapterEntityWrapper<>(recyclerView);
        recyclerAdapterEntityWrapper.setAdapter(new BaseRecyclerViewAdapter<>(dataList, this, baseRecyclerEntityViewManage));
        recyclerAdapterEntityWrapper.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapterEntityWrapper.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemViewClick(View clickView, RecyclerViewHolder viewHolder, String entity, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击了"+entity, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerAdapterEntityWrapper.setOnItemLongClickListener(new OnItemLongClickListener<String>() {
            @Override
            public boolean onItemLongClick(View clickView, RecyclerViewHolder viewHolder, String entity, int position) {
                Toast.makeText(RecyclerViewActivity.this, "长按了"+entity, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    BaseRecyclerEntityViewManage<String> baseRecyclerEntityViewManage = new DefaultBaseRecyclerEntityViewManage<String>(R.layout.item_recyclerview) {
        @Override
        public void updateItemView(Context context, RecyclerViewHolder holder, String entity, int position) {
            TextView button = holder.getView(R.id.button);
            button.setText(entity);
        }
    };
}
