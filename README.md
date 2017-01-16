#EasyBaseAdapter
  该库对BaseAdapter、ViewPagerAdapter、BaseExpandableListAdapter等视图适配器进行了最大化封装，使用泛型模式对需要绑定的数据进行解耦，使其在开发过程中使用更加简洁，减少代码冗余，可使开发者不在关心各类Adapter内部的具体实现细节，更多的把注意力转移到业务逻辑处理中，该库使用非常简单，很容易上手，一看就懂，可使开发者最快速度的完成视图数据的绑定和展示，大大的提高了数据适配器绑定视图的开发效率。

#使用方式

使用非常简单，只需两步即可完成数据的绑定和显示

 1.基于BaseAdapter的列表视图（ListView、GridView等AbsListView）
 
    1）.创建并设定适配器
   
        listView.setAdapter(new BaseEntityViewAdapter<String>(this, dataList, baseAdapterEntityViewManage))
   
    2).创建视图更新回调接口,在此回调接口中实现具体的列表子视图的创建和数据的绑定显示
   
        BaseAdapterEntityViewManage<String> baseAdapterEntityViewManage = new BaseAdapterEntityViewManage<String>() {
           @Override
           public View getAdapterItemView(Context context, String entity, int position) {
               return LayoutInflater.from(context).inflate(R.layout.item_common, null);
           }
   
           @Override
           public void updateAdapterItemView(Context context, View updateView, String entity, int position) {
               TextView textView = (TextView) updateView.findViewById(R.id.tv_item);
               textView.setText(entity);
           }
       };
 
   2.基于PagerAdapter的视图
   
    1）.创建并设定适配器
    viewPager.setAdapter(new BaseEntityPageAdapter<Integer>(this, dataList, baseAdapterEntityViewManage))
    
    2).创建视图更新回调接口,在此回调接口中实现具体的列表子视图的创建和数据的绑定显示
    
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
        
   3.基于BaseExpandableListAdapter的视图
   
    1）.创建并设定适配器
    
    expandableListView.setAdapter(new BaseEntityExpandableListAdapter<GroupEntity, ChildEntity>(expandableListView,
                                            groupEntityList, childEntityList, baseAdapterEntityExpandableListManage))
                                            
    2).创建视图更新回调接口,在此回调接口中实现具体的列表子视图的创建和数据的绑定显示
    
    BaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity> baseAdapterEntityExpandableListManage = new BaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity>() {
            @Override
            public View getGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, GroupEntity groupEntity, int groupPosition) {
                return LayoutInflater.from(context).inflate(R.layout.item_expandlistview_group, null);
            }
    
            @Override
            public void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, View updateGroup, GroupEntity groupEntity, int groupPosition) {
                TextView textView = (TextView) updateGroup.findViewById(R.id.group);
                textView.setText(groupEntity.name);
            }
    
            @Override
            public View getChildView(Context context, ExpandableListView expandListView, ChildEntity childEntity, int groupPosition, int childPosition) {
                return LayoutInflater.from(context).inflate(R.layout.item_expandlistview_child, null);
            }
    
            @Override
            public void updateChildView(Context context, ExpandableListView expandListView, View updateChild, ChildEntity childEntity, int groupPosition, int childPosition) {
                TextView textView = (TextView) updateChild.findViewById(R.id.child);
                textView.setText(childEntity.name);
            }
        };
        
        
#本库还提供了更加快捷和简单的使用方式，代码量更少啊，看看下面^-^
1.对每类数据适配器的视图创建和数据绑定回调接口，都提供了一个相对的抽象的适配接口，在此类接口只需要关心视图的数据绑定和显示即可，这类接口暂支持布局资源的使用
  
    1).基于BaseAdapter和PagerAdapter的视图
    
        private BaseAdapterEntityViewManage<String> baseAdapterEntityViewManage = new DefaultBaseAdapterEntityViewManage<String>(R.layout.item_common) {
             @Override
             public void updateItemView(Context context, View updateView, String entity, int position) {
                 TextView textView = (TextView) updateView.findViewById(R.id.tv_item);
                 textView.setText(entity);
             }
         };
         
    2).基于BaseExpandableListAdapter的视图
    
    DefaultBaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity> baseAdapterEntityExpandableListManage =
                new DefaultBaseAdapterEntityExpandableListManage<GroupEntity, ChildEntity>(R.layout.item_expandlistview_group, R.layout.item_expandlistview_child) {
            @Override
            public void updateGroupView(Context context, ExpandableListView expandListView, boolean isExpanded, View updateGroup, GroupEntity groupEntity, int groupPosition) {
                TextView textView = (TextView) updateGroup.findViewById(R.id.group);
                textView.setText(groupEntity.name);
            }
 
            @Override
            public void updateChildView(Context context, ExpandableListView expandListView, View updateChild, ChildEntity childEntity, int groupPosition, int childPosition) {
                TextView textView = (TextView) updateChild.findViewById(R.id.child);
                textView.setText(childEntity.name);
            }
        };


#该库还为每类适配器封装了一个包装类，可对视图控件和数据适配器的处理进行解耦，直接通过包装类完成相对视图的各类所需接口的设定处理，各类适配器所对应的包装类如下
    
    1.基于BaseAdapter的视图
    adapterViewEntityWrapper = new AdapterViewEntityWrapper<String>(absListView);
    adapterViewEntityWrapper.setAdapter(new BaseEntityViewAdapter<String>(this, dataList, baseAdapterEntityViewManage));
    adapterViewEntityWrapper.setOnEntityViewClickListener(onEntityViewClickListener);
    
    2.基于PagerAdapter的视图
    ViewPagerEntityWrapper<Integer> viewPagerEntityWrapper = new ViewPagerEntityWrapper<Integer>(viewPager);
    viewPagerEntityWrapper.setAdapter(new BaseEntityPageAdapter<Integer>(this, dataList, baseAdapterEntityViewManage));
    viewPagerEntityWrapper.setOnEntityViewPagerClickListener(onEntityViewPagerClickListener);
    
    3.基于BaseExpandableListAdapter的视图
    ExpandableListViewAdapterWrapper<GroupEntity, ChildEntity> expandableListViewAdapterWrapper =
                    new ExpandableListViewAdapterWrapper<GroupEntity, ChildEntity>(expandableListView);
    expandableListViewAdapterWrapper.setBaseEntityExpandableListAdapter(new BaseEntityExpandableListAdapter<GroupEntity, ChildEntity>(expandableListView,
                            groupEntityList, childEntityList, baseAdapterEntityExpandableListManage));
    expandableListViewAdapterWrapper.setOnEntityExpandableListClickListener(onEntityExpandableListClickListener);