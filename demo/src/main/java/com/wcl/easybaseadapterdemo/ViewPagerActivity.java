package com.wcl.easybaseadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wcl.easybaseadapter.entityviewmanage.BaseAdapterEntityViewManage;
import com.wcl.easybaseadapter.viewpager.BaseEntityPageAdapter;
import com.wcl.easybaseadapter.viewpager.ViewPagerEntityWrapper;
import com.wcl.easybaseadapter.viewpager.listener.OnEntityViewPagerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager使用demo
 */
public class ViewPagerActivity extends Activity{

    ViewPager viewPager;
    TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv_info = (TextView) findViewById(R.id.tv_info);

        List<Integer> dataList = new ArrayList<Integer>();
        dataList.add(Color.BLUE);
        dataList.add(Color.GRAY);
        dataList.add(Color.MAGENTA);
        dataList.add(Color.RED);
        dataList.add(Color.YELLOW);

        viewPager.addOnPageChangeListener(onPageChangeListener);

        ViewPagerEntityWrapper<Integer> viewPagerEntityWrapper = new ViewPagerEntityWrapper<Integer>(viewPager);
        viewPagerEntityWrapper.setAdapter(new BaseEntityPageAdapter<Integer>(this, dataList, baseAdapterEntityViewManage));
        viewPagerEntityWrapper.setOnEntityViewPagerClickListener(new OnEntityViewPagerClickListener<Integer>() {
            @Override
            public void onEntityViewClick(ViewPager parent, View clickView, Integer entity, int position) {
                Toast.makeText(ViewPagerActivity.this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
            }
        });

    }

    BaseAdapterEntityViewManage<Integer> baseAdapterEntityViewManage = new BaseAdapterEntityViewManage<Integer>() {
        @Override
        public View getAdapterItemView(Context context, Integer entity, int position) {
            return LayoutInflater.from(context).inflate(R.layout.item_viewpager, null);
        }

        @Override
        public void updateAdapterItemView(Context context, View updateView, Integer entity, int position) {
            updateView.setBackgroundColor(entity);
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            tv_info.setText(position+"");
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
