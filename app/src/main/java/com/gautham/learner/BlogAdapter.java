package com.gautham.learner;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder>{
    private Context context;
    private List<Blog> blogss;

    public BlogAdapter(Context context, List<Blog> blogss) {
        this.context = context;
        this.blogss = blogss;
    }

    @NonNull
    @Override
    public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.blog_post,parent,false);
        return new BlogAdapter.ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
        final Blog blogssw = blogss.get(position);
        holder.etiitle.setText(blogssw.getTitle());
        holder.daate.setText(blogssw.getDate());
        holder.des.setText(blogssw.getBlog());
        String e = blogssw.getLinks();
        holder.ltink.setText(e);
        Linkify.addLinks(holder.ltink, Linkify.WEB_URLS);
        holder.ltink.setMovementMethod(LinkMovementMethod.getInstance());

    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return blogss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView etiitle,daate,des,ltink;

        public ViewHolder(View itemview) {
            super(itemview);
            etiitle=itemview.findViewById(R.id.unametv);
            daate=itemview.findViewById(R.id.utim);
            des=itemview.findViewById(R.id.ptitletv);
            ltink=itemview.findViewById(R.id.ers);
        }
    }
}
