package com.wcl.easybaseadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wcl.easybaseadapter.adapterview.AdapterViewEntityWrapper;
import com.wcl.easybaseadapter.adapterview.BaseEntityViewAdapter;
import com.wcl.easybaseadapter.adapterview.listener.OnEntityViewClickListener;
import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.viewholder.EntityViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * demo 入口
 */
public class MainActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview_main);

        List<Item> dataList = new ArrayList<Item>();
        dataList.add(new Item("ListView", ListViewActivity.class));
        dataList.add(new Item("GridView", GridViewActivity.class));
        dataList.add(new Item("ViewPager", ViewPagerActivity.class));
        dataList.add(new Item("ExpandListView", ExpandableListViewActivity.class));
        dataList.add(new Item("RecyclerView", RecyclerViewActivity.class));

        AdapterViewEntityWrapper<Item> adapterViewEntityWrapper = new AdapterViewEntityWrapper<Item>(listView);
        adapterViewEntityWrapper.setAdapter(new BaseEntityViewAdapter<Item>(this, dataList, new BaseAdapterEntityViewManage<Item>() {
            @Override
            public View getAdapterItemView(Context context, Item entity, int position) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.item_main, null);
                TextView textView = (TextView) inflate.findViewById(R.id.item_main);
                return inflate;
            }

            @Override
            public void updateAdapterItemView(Context context, EntityViewHolder holder, Item entity, int position) {
                TextView textView = (TextView) holder.findViewById(R.id.item_main);
                textView.setText(entity.name);
            }
        }));
        adapterViewEntityWrapper.setOnEntityViewClickListener(new OnEntityViewClickListener<Item>() {
            @Override
            public void onEntityViewClick(AdapterView<?> parent, View clickView, Item entity, int position) {
                startActivity(new Intent(MainActivity.this, entity.cls));
            }
        });
    }

    class Item {
        public Item(String name, Class cls) {
            this.name = name;
            this.cls = cls;
        }

        String name;
        Class cls;
    }
}
