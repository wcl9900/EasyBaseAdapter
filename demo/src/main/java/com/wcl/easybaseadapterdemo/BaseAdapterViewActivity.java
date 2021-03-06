package com.wcl.easybaseadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.wcl.easybaseadapter.adapterview.AdapterViewEntityWrapper;
import com.wcl.easybaseadapter.adapterview.BaseEntityViewAdapter;
import com.wcl.easybaseadapter.adapterview.listener.OnEntityViewClickListener;
import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.viewholder.EntityViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * AdapterView通用适配器基类
 */
public class BaseAdapterViewActivity extends Activity {
    AbsListView absListView;
    private AdapterViewEntityWrapper<String> adapterViewEntityWrapper;

    protected void init(){
        absListView = (AbsListView) findViewById(R.id.abslistview);

        List<String> dataList = new ArrayList<String>();
        for (int i = 0; i < 30; i++){
            dataList.add("item>>>"+i);
        }

        adapterViewEntityWrapper = new AdapterViewEntityWrapper<String>(absListView);
        adapterViewEntityWrapper.setAdapter(new BaseEntityViewAdapter<String>(this, dataList, baseAdapterEntityViewManage));
        adapterViewEntityWrapper.setOnEntityViewClickListener(onEntityViewClickListener);
    }
    int newIndex;
    private BaseAdapterEntityViewManage<String> baseAdapterEntityViewManage = new BaseAdapterEntityViewManage<String>() {
        @Override
        public View getAdapterItemView(Context context, String entity, int position) {
            Log.d("BaseEntityViewAdapter", "New Item>>>>>"+(newIndex++));
            return LayoutInflater.from(context).inflate(R.layout.item_common, null);
        }

        @Override
        public void updateAdapterItemView(Context context, EntityViewHolder<String> holder, String entity, int position) {
            TextView textView = (TextView) holder.findViewById(R.id.tv_item);
            textView.setText(entity);
        }
    };

//    private BaseRecyclerEntityViewManage<String> baseAdapterEntityViewManage = new DefaultBaseRecyclerEntityViewManage<String>(R.layout.item_common) {
//        @Override
//        public void updateItemView(Context context, EntityViewHolder<String> holder, String entity, int position) {
//            TextView textView = (TextView) holder.findViewById(R.id.tv_item);
//            textView.setText(entity);
//        }
//    };

    private OnEntityViewClickListener<String> onEntityViewClickListener = new OnEntityViewClickListener<String>() {
        @Override
        public void onEntityViewClick(AdapterView<?> parent, View clickView, String entity, int position) {
            Toast.makeText(BaseAdapterViewActivity.this, "当前点击了第" + position + "个", Toast.LENGTH_SHORT).show();
        }
    };
}
