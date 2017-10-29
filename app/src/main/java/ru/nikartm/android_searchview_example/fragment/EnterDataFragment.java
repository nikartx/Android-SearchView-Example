package ru.nikartm.android_searchview_example.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.nikartm.android_searchview_example.R;
import ru.nikartm.android_searchview_example.util.AppProgressDialog;
import ru.nikartm.android_searchview_example.util.Util;
import ru.nikartm.github_api.GitHubManager;
import ru.nikartm.github_api.model.Follower;


public class EnterDataFragment extends Fragment {

    public static final String TAG = EnterDataFragment.class.getSimpleName();

    private Disposable disposable;

    private TextInputEditText tietUserName;
    private Button btnSearchFollowers;

    public EnterDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_data, container, false);
        tietUserName = (TextInputEditText) view.findViewById(R.id.tiet_username);
        btnSearchFollowers = (Button) view.findViewById(R.id.btn_search_followers);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnSearchFollowers.setOnClickListener(v -> getFollowers(tietUserName.getText().toString()));
    }

    // Loading user followers from github
    private void getFollowers(String userName) {
        Util.hideKeyboard(getActivity());
        disposable = GitHubManager.getInstance(getActivity())
                .getFollowers(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> AppProgressDialog.show(getActivity()))
                .doOnTerminate(AppProgressDialog::dismiss)
                .subscribe(this::initFollowersList,
                        e -> {
                            initFailure();
                            Log.e(TAG, "Get followers error", e);
                        });
    }

    // Show follower list
    private void initFollowersList(List<Follower> followers) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, FollowersFragment.getInstance(followers))
                .addToBackStack(TAG)
                .commit();
    }

    // Show failure message
    private void initFailure() {
        Toast.makeText(getContext(), "Error getting Follower list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
