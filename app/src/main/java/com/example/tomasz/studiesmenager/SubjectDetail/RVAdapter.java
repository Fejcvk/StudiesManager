package com.example.tomasz.studiesmenager.SubjectDetail;

import android.content.Context;
import android.support.transition.TransitionManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by hub on 2017-05-27.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AttendenceViewHolder>
    implements View.OnClickListener{

    private List<Attendence> attendences;
    private int expandedPosition = -1;
    private RecyclerView recyclerView;
    private Context ctx;
    public RVAdapter(List<Attendence> pastAttendences, RecyclerView rv, Context context)
    {
        attendences = pastAttendences;
        recyclerView = rv;
        ctx = context;

    }
    @Override
    public AttendenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_subject_detail, parent, false);
        AttendenceViewHolder pvh = new AttendenceViewHolder(v);
        pvh.itemView.setOnClickListener(RVAdapter.this);
        pvh.itemView.setTag(pvh);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final AttendenceViewHolder holder, int position) {

        final Attendence a = attendences.get(position);
        if (a == null) return;
        String classType = "";
        int color = 0xffffffff;

        switch(a.Class.Type)
        {
            case Lab:
                classType = "Laboratorium";
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

        if (position == expandedPosition) {
            holder.details.setVisibility(View.VISIBLE);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String t = holder.et.getText().toString();
                    try {
                        if (t.equals(""))
                            a.PointsEarned = 0;
                        else
                            a.PointsEarned = Integer.parseInt(t);
                        a.WasPresent = !a.WasPresent;
                        a.save();
                        expandedPosition = -1;
                        holder.details.setVisibility(View.GONE);
                    }catch(NumberFormatException e)
                    {

                    }
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });
        } else {
            holder.details.setVisibility(View.GONE);
        }

        holder.cv.setBackgroundColor(color);
        if(a.WasPresent) {
            holder.iv.setImageDrawable(ctx.getDrawable(R.drawable.ic_done_black_24dp));
            holder.btn.setText("Nieobecny");
        }
        else {
            holder.iv.setImageDrawable(ctx.getDrawable(R.drawable.ic_close_black_24dp));
            holder.btn.setText("Obecny");
        }

        holder.classType.setText(classType);
        if (a.PointsEarned != 0)
            holder.et.setText(String.valueOf(a.PointsEarned));
        else
            holder.et.setText("");



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        holder.date.setText(sdf.format(a.Date));
    }

    @Override
    public int getItemCount() {
        return attendences.size();
    }

    @Override
    public void onClick(View view) {
        AttendenceViewHolder holder = (AttendenceViewHolder) view.getTag();
        Attendence a = attendences.get(holder.getAdapterPosition());

        // Check for an expanded view, collapse if you find one
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            if(expandedPosition == holder.getAdapterPosition()) {
                expandedPosition = -1;
                notifyItemChanged(prev);
                return;
            }
            notifyItemChanged(prev);
        }
        // Set the current position to "expanded"
        expandedPosition = holder.getAdapterPosition();
        notifyItemChanged(expandedPosition);
    }

    public static class AttendenceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView classType;
        TextView date;
        LinearLayout details;
        ImageView iv;
        Button btn;
        EditText et;

        AttendenceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_template);
            classType = (TextView)itemView.findViewById(R.id.card_subject_type_name);
            date = (TextView)itemView.findViewById(R.id.card_subject_date);
            details = (LinearLayout) itemView.findViewById(R.id.details);
            btn = (Button) itemView.findViewById(R.id.btnPresent);
            et = (EditText) itemView.findViewById(R.id.pointsEarned);
            iv = (ImageView) itemView.findViewById(R.id.was_there_photo);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}