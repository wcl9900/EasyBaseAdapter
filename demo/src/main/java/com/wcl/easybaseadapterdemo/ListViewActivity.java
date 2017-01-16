package com.wcl.easybaseadapterdemo;

import android.os.Bundle;

/**
 * ListView 使用demo
 */
public class ListViewActivity extends BaseAdapterViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        init();
    }
}
