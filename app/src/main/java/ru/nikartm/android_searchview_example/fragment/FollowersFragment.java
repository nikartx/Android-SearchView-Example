package ru.nikartm.android_searchview_example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.nikartm.android_searchview_example.R;
import ru.nikartm.android_searchview_example.adapter.AdapterClickListener;
import ru.nikartm.android_searchview_example.adapter.FollowerListAdapter;
import ru.nikartm.github_api.model.Follower;


public class FollowersFragment extends Fragment implements AdapterClickListener {

    public static final String FOLLOWERS = "followers";

    private FollowerListAdapter adapter;
    private RecyclerView recyclerView;
    private List<Follower> followers;

    public FollowersFragment() {
    }

    public static FollowersFragment getInstance(List<Follower> followers) {
        FollowersFragment fragment = new FollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FOLLOWERS, (ArrayList<Follower>) followers);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_follower_list);
        if (getArguments() != null) {
            followers = getArguments().getParcelableArrayList(FOLLOWERS);
        }
        onFollowerList();
        return view;
    }

    private void onFollowerList() {
        adapter = new FollowerListAdapter(getActivity(), followers);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "Show GitHub " + followers.get(position).getLogin(), Toast.LENGTH_SHORT).show();
    }

}
