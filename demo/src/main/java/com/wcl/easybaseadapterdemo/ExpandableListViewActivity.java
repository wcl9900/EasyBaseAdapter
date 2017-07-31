package com.wcl.easybaseadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wcl.easybaseadapter.expandablelistView.BaseAdapterEntityExpandableListManage;
import com.wcl.easybaseadapter.expandablelistView.BaseEntityExpandableListAdapter;
import com.wcl.easybaseadapter.expandablelistView.ExpandableListViewAdapterWrapper;
import com.wcl.easybaseadapter.expandablelistView.listener.OnEntityExpandableListClickListener;
import com.wcl.easybaseadapter.viewholder.ViewChildHolder;
import com.wcl.easybaseadapter.viewholder.ViewGroupHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ExpandableListView 使用demo
 */
public class ExpandableListViewActivity extends Activity {

    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        expandableListView = (ExpandableListView)findViewById(R.id.expandlistview);

        List<GroupEntity> groupEntityList = new ArrayList<GroupEntity>();
        List<List<ChildEntity>> childEntityList = new ArrayList<List<ChildEntity>>();
        for (int group = 0; group < 20; group ++){
            groupEntityList.add(new GroupEntity("Group"+group));
            List<ChildEntity> childEntities = new ArrayList<ChildEntity>();
            childEntityList.add(childEntities);
            for (int child = 0; child < 10; child ++){
                ChildEntity childEntity = new ChildEntity("item:"+child);
                childEntities.add(childEntity);
            }
        }

        ExpandableListViewAdapterWrapper<GroupEntity, ChildEntity> expandableListViewAdapterWrapper =
                new ExpandableListViewAdapterWrapper<GroupEntity, ChildEntity>(expandableListView);
        expandableListViewAdapterWrapper.setBaseEntityExpandableListAdapter(
                new BaseEntityExpandableListAdapter<GroupEntity, ChildEntity>(expandableListView,
                        groupEntityList, childEntityList, baseAdapterEntityExpandableListManage));
        expandableListViewAdapterWrapper.setOnEntityExpandableListClickListener(onEntityExpandableListClickListener);
    }

    BaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity> baseAdapterEntityExpandableListManage = new BaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity>() {
        @Override
        public View getGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, GroupEntity groupEntity, int groupPosition) {
            return LayoutInflater.from(context).inflate(R.layout.item_expandlistview_group, null);
        }

        @Override
        public void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, ViewGroupHolder<GroupEntity> viewGroupHolder, GroupEntity groupEntity, int groupPosition) {
            TextView textView = (TextView) viewGroupHolder.findViewById(R.id.group);
            textView.setText(groupEntity.name);
        }

        @Override
        public View getChildView(Context context, ExpandableListView expandListView, ChildEntity childEntity, int groupPosition, int childPosition) {
            return LayoutInflater.from(context).inflate(R.layout.item_expandlistview_child, null);
        }

        @Override
        public void updateChildView(Context context, ExpandableListView expandListView, ViewChildHolder<GroupEntity, ChildEntity> viewChildHolder, ChildEntity childEntity, int groupPosition, int childPosition) {
            TextView textView = (TextView) viewChildHolder.findViewById(R.id.child);
            textView.setText(childEntity.name);
        }
    };

//    DefaultBaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity> baseAdapterEntityExpandableListManage =
//            new DefaultBaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity>(R.layout.item_expandlistview_group, R.layout.item_expandlistview_child) {
//                @Override
//                public void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, ViewGroupHolder<GroupEntity> viewGroupHolder, GroupEntity groupEntity, int groupPosition) {
//                    TextView textView = (TextView) viewGroupHolder.findViewById(R.id.group);
//                    textView.setText(groupEntity.name);
//                }
//
//                @Override
//                public void updateChildView(Context context, ExpandableListView expandListView, ViewChildHolder<GroupEntity, ChildEntity> viewChildHolder, ChildEntity childEntity, int groupPosition, int childPosition) {
//                    TextView textView = (TextView) viewChildHolder.findViewById(R.id.child);
//                    textView.setText(childEntity.name);
//                }
//    };

    OnEntityExpandableListClickListener<GroupEntity, ChildEntity> onEntityExpandableListClickListener = new OnEntityExpandableListClickListener<GroupEntity, ChildEntity>() {
        @Override
        public boolean onGroupClick(Context context, ExpandableListView expandListView, View groupView, GroupEntity groupEntity, int groupPosition) {
            Toast.makeText(context, "点击了第"+groupPosition + "组", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onChildClick(Context context, ExpandableListView expandListView, View childView, GroupEntity groupEntity, ChildEntity childEntity, int groupPosition, int childPosition) {
            Toast.makeText(context, "点击了第"+groupPosition + "组,第"+childPosition+"个子节点", Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    class GroupEntity{
        String name;
        public GroupEntity(String name) {
            this.name = name;
        }
    }
    class ChildEntity{
        String name;
        public ChildEntity(String name) {
            this.name = name;
        }
    }
}
