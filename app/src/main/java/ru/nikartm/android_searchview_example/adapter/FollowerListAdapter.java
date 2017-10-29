package ru.nikartm.android_searchview_example.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.nikartm.android_searchview_example.R;
import ru.nikartm.android_searchview_example.util.ImageReader;
import ru.nikartm.github_api.model.Follower;

/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.FollowerViewHolder> {

    private static final String TAG = FollowerListAdapter.class.getSimpleName();

    private Context context;
    private List<Follower> followers;
    private static AdapterClickListener clickListener = null;

    public FollowerListAdapter(Context context, List<Follower> followers) {
        this.context = context;
        this.followers = followers;
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(AdapterClickListener clickListener) {
        FollowerListAdapter.clickListener = clickListener;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        if (followers.size() > 0) {
            Follower follower = followers.get(position);
            if (follower != null) {
                ImageReader.setCircleImage(context, holder.ivLogo, follower.getAvatarUrl());
                holder.tvUserName.setText(follower.getLogin());
                holder.tvUserId.setText(String.valueOf(follower.getId()));
                holder.tvGithubURL.setText(follower.getUrl());
            }
        }
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
        return followers == null ? 0 : followers.size();
    }

}
