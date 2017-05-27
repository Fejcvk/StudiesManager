package com.example.tomasz.studiesmenager.SubjectDetail;

import android.support.transition.TransitionManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.R;

import java.util.List;

/**
 * Created by hub on 2017-05-27.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AttendenceViewHolder>{

    private List<Attendence> attendences;
    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;
    public RVAdapter(List<Attendence> pastAttendences, RecyclerView rv)
    {
        attendences = pastAttendences;
        recyclerView = rv;

    }
    @Override
    public AttendenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_subject_detail, parent, false);
        AttendenceViewHolder pvh = new AttendenceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final AttendenceViewHolder holder, int position) {

        Attendence a = attendences.get(position);
        if (a == null) return;
        String classType = "";
        int color = 0xffffffff;

        switch(a.Class.Type)
        {
            case Lab:
                classType = "Laboratiorum";
                color = 0xff909090;
                break;

            case Class:
                classType = "Ćwiczenia";
                color = 0xffdeadbe;
                break;

            case Lecture:
                classType = "Wykład";
                color = 0xffbeeffa;
                break;
        }
        final boolean isExpanded = position == mExpandedPosition;
        holder.details.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : holder.getAdapterPosition();
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();
            }
        });
        holder.cv.setBackgroundColor(color);
        holder.classType.setText(classType);
        holder.date.setText(a.Date.toString());
    }

    @Override
    public int getItemCount() {
        return attendences.size();
    }

    public static class AttendenceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView classType;
        TextView date;
        LinearLayout details;

        AttendenceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_template);
            classType = (TextView)itemView.findViewById(R.id.card_subject_type_name);
            date = (TextView)itemView.findViewById(R.id.card_subject_date);
            details = (LinearLayout) itemView.findViewById(R.id.details);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}