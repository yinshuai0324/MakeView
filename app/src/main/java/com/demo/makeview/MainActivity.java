package com.demo.makeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.makeview.view.MakeView;
import com.demo.makeview.view.adapter.MakeTimesAdapter;
import com.demo.makeview.view.bean.MakeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinshuai
 */
public class MainActivity extends AppCompatActivity {

    private MakeView makeView;

    private List<String> times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeView = findViewById(R.id.makeView);
        initTimes();
        makeView.setDataBean(getData());
        makeView.setTimeData(times);

        makeView.setOnMakeClickListener(new MakeView.OnMakeClickListener() {
            @Override
            public void staffItemClick(MakeBean bean) {
                Toast.makeText(MainActivity.this, bean.getName() + "休假点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void timeClick(MakeBean.MakeItemBean bean, MakeTimesAdapter adapter, int position) {
                Toast.makeText(MainActivity.this, bean.getTime() + "点击", Toast.LENGTH_SHORT).show();
                bean.setRemark("aaaaa");
                bean.setOccupy(true);
                adapter.notifyItemChanged(position);
            }
        });
    }


    public void initTimes() {
        times.add("10:00");
        times.add("11:00");
        times.add("12:00");
        times.add("13:00");
        times.add("14:00");
        times.add("15:00");
        times.add("16:00");
        times.add("17:00");
        times.add("18:00");
        times.add("19:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
        times.add("20:00");
    }


    public List<MakeBean> getData() {
        List<MakeBean> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            MakeBean makeBean = new MakeBean();
            makeBean.setName("技师" + i);
            if (i % 2 == 0) {
                makeBean.setPost("高级理发师");
            } else {
                makeBean.setPost("学徒");
            }

            List<MakeBean.MakeItemBean> list = new ArrayList<>();
            for (int j = 0; j < times.size(); j++) {
                MakeBean.MakeItemBean bean = new MakeBean.MakeItemBean();
                bean.setEnabled(true);
                bean.setOccupy(false);
                bean.setTime(times.get(j));
                bean.setRemark("备注1");
                list.add(bean);
            }
            makeBean.setTimes(list);

            data.add(makeBean);

        }
        return data;
    }
}
