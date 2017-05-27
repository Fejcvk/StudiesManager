package com.example.tomasz.studiesmenager.SubjectsListCardView;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomasz.studiesmenager.Model.Subject;
import com.example.tomasz.studiesmenager.R;
import com.example.tomasz.studiesmenager.SubjectDetail.SubjectDetailActivity;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {

    private List<Subject> subjectList;
    private static SubjectClickListener subjectClickListener;

    public SubjectsAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public LinearLayout linlayout;
        public SubjectViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.subject_title);
            linlayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        }
    }

    public void setOnItemClickListener(SubjectClickListener _subjectClickListener) {
        this.subjectClickListener = _subjectClickListener;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_subjects_card, parent, false);

        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.title.setText(subject.Name);

        holder.linlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SubjectDetailActivity.class);
                intent.putExtra("Subject",  subjectList.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    public void addItem(Subject subject, int index) {
        subjectList.add(index, subject);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        subjectList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public interface SubjectClickListener {
        public void onItemClick(int position, View v);
    }
}
