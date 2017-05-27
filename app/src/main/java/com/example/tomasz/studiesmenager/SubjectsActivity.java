package com.example.tomasz.studiesmenager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomasz.studiesmenager.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addSubject);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.subject_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectsAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

//        ((SubjectsAdapter) mAdapter).setOnItemClickListener(new SubjectsAdapter.SubjectClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                Toast.makeText(getApplicationContext(), ((TextView) v).getText(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private List<Subject> getDataSet() {
        List<Subject> results = Subject.listAll(Subject.class);
//        ArrayList results = new ArrayList<Subject>();
//        for (int index = 0; index < 20; index++) {
//            Subject obj = new Subject("Some Primary Text " + index);
//            results.add(index, obj);
//        }
        return results;
    }

}
