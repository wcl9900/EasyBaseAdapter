package com.wcl.easybaseadapterdemo;

import android.os.Bundle;

/**
 * GridView 和 ListView都是基于AbsListView 所以两者使用方式完全一样，再这写这个GridView的使用demo是为了更好的说明一下适配器的使用方式的灵活性
 */
public class GridViewActivity extends BaseAdapterViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        init();
    }
}
