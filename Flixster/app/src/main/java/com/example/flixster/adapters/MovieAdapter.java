package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.DetailActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(Html.fromHtml(" <i> "+movie.getTitle()+"</i>"));
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            }
            else {
                imageUrl = movie.getPosterPath();
            }

            Glide.with(context).load(imageUrl).fitCenter().transform(new RoundedCorners(30)).into(ivPoster);
            container.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent i = new Intent(context, DetailActivity.class);
                     i.putExtra("movie", Parcels.wrap(movie));

//                     ActivityOptionsCompat options = ActivityOptionsCompat.
//                             makeSceneTransitionAnimation((Activity)context, (View)tvTitle, "movieTitleTransition");
//                     context.startActivity(i, options.toBundle());
                     context.startActivity((i));
                 }
//
//                @Override
//                public void onClick(View v) {
////                    final Movie movie = (Movie)v.getTag();
////                    if (movie != null) {
//                        Intent i = new Intent(context, DetailActivity.class);
//                        i.putExtra(DetailActivity.EXTRA_MOVIE, movie);
//                        // Check if we're running on Android 5.0 or higher
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Pair<View, String> p1 = Pair.create((View)tvTitle, "title");
//                            Pair<View, String> p2 = Pair.create((View)tvOverview, "overview");
////                            Pair<View, String> p3 = Pair.create(ratingBar, "ratingBar");
////                            Pair<View, String> p4 = Pair.create(player, "youTubePlayerView");
//                            final ActivityOptionsCompat options = ActivityOptionsCompat.
//                                    makeSceneTransitionAnimation((Activity) context,p1,p2);
//                            context.startActivity(i, options.toBundle());
//
//                        } else {
//                            // Implement this feature without material design
//                            context.startActivity(i);
//                        }
//
////                    }
//                }
            });
        }
    }
}

