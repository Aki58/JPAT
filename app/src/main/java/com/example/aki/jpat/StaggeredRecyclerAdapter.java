package com.example.aki.jpat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aki on 8/13/2017.
 */
public class StaggeredRecyclerAdapter extends RecyclerView.Adapter<StaggeredRecyclerAdapter.ViewHolder> {

    private List<Data> items;

    private Context c;

    public StaggeredRecyclerAdapter(List<Data> items,Context c) {
        this.items = items;
        this.c=c;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.s_item, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
       String author=items.get(position).getAuthor();
        String title=items.get(position).getTitle();
        String desc=items.get(position).getDesc();
        String time=items.get(position).getTime();
        String image=items.get(position).getImage();
        final String url=items.get(position).getUrl();
        holder.setImage(c,image);
        holder.author.setText(author);
        holder.title.setText(title);
        holder.desc.setText(desc);
        holder.time.setText(time);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                c.startActivity(browserIntent);

            }
        });

    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        public TextView title,author,desc,time;
        public ImageView post_image;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            post_image=(ImageView)itemView.findViewById(R.id.postImage);
            title= (TextView) itemView.findViewById(R.id.sTitle);
            author= (TextView) itemView.findViewById(R.id.sAuthor);
            desc= (TextView) itemView.findViewById(R.id.sDesc);
            time= (TextView) itemView.findViewById(R.id.sTime);
        }
        public void setImage(final Context ctx, final String image)
        {

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {
                    Picasso.with(ctx).load(image).into(post_image);
                }

                @Override
                public void onError() {

                }
            });
        }
    }

}
