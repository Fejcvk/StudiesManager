package com.example.tomasz.studiesmenager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.example.tomasz.studiesmenager.Model.Attendence;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.in;

public class ScheduleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);
        List<Attendence> al = Attendence.listAll(Attendence.class);

        Collections.sort(al, new Comparator<Attendence>() {
            @Override
            public int compare(Attendence o1, Attendence o2) {
                return o1.Date.compareTo(o2.Date);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ScheduleAdapterCardView(al);
        mRecyclerView.setAdapter(mAdapter);

        int count = 0;
        Date now = new Date();
        for (Iterator<Attendence> i = al.iterator(); i.hasNext();) {
            Attendence item = i.next();
            if (item.Date.compareTo(now) < 0) count++;
            else break;
        }
        mLayoutManager.scrollToPosition(count);

    }


}
