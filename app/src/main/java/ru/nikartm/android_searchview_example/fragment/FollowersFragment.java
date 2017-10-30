package ru.nikartm.android_searchview_example.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.followers);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
        initSearch(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initSearch(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
