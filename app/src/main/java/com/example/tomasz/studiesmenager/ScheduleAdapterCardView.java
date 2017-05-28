package com.example.tomasz.studiesmenager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


//import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.R;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import static com.example.tomasz.studiesmenager.R.color.colorLab;
import static com.example.tomasz.studiesmenager.R.color.colorLec;
import static com.example.tomasz.studiesmenager.R.color.colorTut;


/**
 * Created by R50 on 2017-05-27.
 */

public class ScheduleAdapterCardView extends RecyclerView.Adapter<ScheduleAdapterCardView.AttendenceViewHolder> {
    private List<Attendence> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScheduleAdapterCardView(List<Attendence> al) {
        mDataset = al;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttendenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.schedulecardview, parent, false);
        //ViewHolder vh = new ViewHolder(v);
        //return vh;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedulecardview, parent, false);


        return new AttendenceViewHolder(itemView);
    }


    /*
    public AttendenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_subject_detail, parent, false);
        AttendenceViewHolder pvh = new AttendenceViewHolder(v);
        return pvh;
    }
    */
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AttendenceViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset.get(position));

        final Attendence a = mDataset.get(position);
        if (a == null) return;
        String classType = "";
        int color = 0xff9365b8;

        switch(a.Class.Type) {
            case Lab:
                classType = "Laboratorium";
                color = 0xff9365b8;
                break;

            case Class:
                classType = "Ćwiczenia";
                color = 0xfff7da64;
                break;

            case Lecture:
                classType = "Wykład";
                color = 0xff54acd2;
                break;
        }

        holder.cardLinearLayout.setBackgroundColor(color);
//        holder.classType.setText(classType);
        holder.title.setText(a.Class.Subject.Name);

        TimeZone tz = TimeZone.getDefault ();
        Calendar c = Calendar.getInstance (tz);
        Calendar h = Calendar.getInstance(tz);
        c.setTime(a.Date);
        h.setTime(a.Class.StartHour);
        //int cDay = c.get(Calendar.DAY_OF_MONTH);
        holder.date.setText(Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + "." + Integer.toString(c.get(Calendar.MONTH)));
        holder.time.setText(Integer.toString(h.get(Calendar.HOUR)) + ":" + Integer.toString(h.get(Calendar.MINUTE)));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public static class AttendenceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView date;
        TextView time;
        LinearLayout cardLinearLayout;
        //TextView att;
        TextView points;
        TextView classType;


        AttendenceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_template);
            title = (TextView)itemView.findViewById(R.id.card_subject_title);
            date = (TextView)itemView.findViewById(R.id.card_subject_date);
            time = (TextView) itemView.findViewById(R.id.card_subject_time);
            cardLinearLayout = (LinearLayout) itemView.findViewById(R.id.card_subject_bg);
            //att = (TextView) itemView.findViewById(R.id.card_subject_att); //+ %
            //points - not implemented

        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}