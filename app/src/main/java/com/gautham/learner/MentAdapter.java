package com.gautham.learner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MentAdapter extends RecyclerView.Adapter<MentAdapter.ViewHolder> {
    private Context context;
    private List<Mentoruser> Ments;

    public MentAdapter(Context context, List<Mentoruser> Ments) {
        this.context=context;
        this.Ments=Ments;
    }

    @NonNull
    @Override
    public MentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.mentuserq,parent,false);
        return new MentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentAdapter.ViewHolder holder, int position) {
        final Mentoruser blogssw=Ments.get(position);
        holder.nameq.setText(blogssw.getMentorname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, Blogshowing.class);
               i.putExtra("id",blogssw.getId());
                context.startActivity(i);

            }
        });
    }





    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return Ments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameq;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameq=itemView.findViewById(R.id.Mqname);
        }
    }
}
