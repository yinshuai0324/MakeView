####自定义预约View

- 效果图
![效果图](https://github.com/yinshuai0324/MakeView/blob/master/image/screen_20190426183521.png "百度一下，你就知道")
- 使用方法

````
        //设置主体数据
        makeView.setDataBean(getData());
        //设置顶部时间的数据
        makeView.setTimeData(times);
        
        //设置控件的监听回调
        makeView.setOnMakeClickListener(new MakeView.OnMakeClickListener() {
            @Override
            public void staffItemClick(MakeBean bean) {
            //左边员工点击事件监听
                Toast.makeText(MainActivity.this, bean.getName() + "休假点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void timeClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position) {
                //内容点击监听 可直接修改数据 然后更新对应的item
                Toast.makeText(MainActivity.this, bean.getTime() + "点击", Toast.LENGTH_SHORT).show();
                //修改数据
                bean.setRemark("aaaaa");
                bean.setOccupy(true);
                //更新对应的item 不需要全部更新
                adapter.notifyItemChanged(position);
            }
        });
````