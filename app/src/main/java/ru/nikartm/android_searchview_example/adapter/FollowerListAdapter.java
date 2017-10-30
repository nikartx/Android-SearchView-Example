package ru.nikartm.android_searchview_example.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.nikartm.android_searchview_example.R;
import ru.nikartm.android_searchview_example.util.ImageReader;
import ru.nikartm.android_searchview_example.util.Util;
import ru.nikartm.github_api.model.Follower;

/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.FollowerViewHolder> implements Filterable {

    private static final String TAG = FollowerListAdapter.class.getSimpleName();

    private Context context;
    private List<Follower> followers;
    private List<Follower> filteredList;

    public FollowerListAdapter(Context context, List<Follower> followers) {
        this.context = context;
        this.followers = followers;
        this.filteredList = followers;
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivLogo;
        TextView tvUserName;
        TextView tvUserId;
        TextView tvGithubURL;

        public FollowerViewHolder(final View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cv_follower);
            ivLogo = (ImageView) view.findViewById(R.id.iv_user_logo);
            tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
            tvUserId = (TextView) view.findViewById(R.id.tv_user_id);
            tvGithubURL = (TextView) view.findViewById(R.id.tv_github_url);
        }
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        if (filteredList.size() > 0) {
            Follower follower = filteredList.get(position);
            if (follower != null) {
                holder.cardView.setOnClickListener(v -> onCardClick(position));
                ImageReader.setCircleImage(context, holder.ivLogo, follower.getAvatarUrl());
                holder.tvUserName.setText(follower.getLogin());
                holder.tvUserId.setText(String.valueOf(follower.getId()));
                holder.tvGithubURL.setText(follower.getUrl());
            }
        }
    }

    // Handle click by card github user
    private void onCardClick(int position) {
        Toast.makeText(context, "Show GitHub " + filteredList.get(position).getLogin(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = followers;
                } else {
                    filteredList = Util.searchFollowersFilter(followers, charString);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Follower>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

}
