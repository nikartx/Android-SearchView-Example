package ru.nikartm.android_searchview_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.nikartm.android_searchview_example.util.AppProgressDialog;
import ru.nikartm.github_api.GitHubManager;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFollowers();
    }

    private void getFollowers() {
        disposable = GitHubManager.getInstance(getApplicationContext())
                .getFollowers("nikartm")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> AppProgressDialog.show(this))
                .doOnTerminate(AppProgressDialog::dismiss)
                .subscribe(followers -> Log.d(TAG, "Followers list size: " + followers.size()),
                        e -> Log.e(TAG, "Get followers error", e));
    }

    private void initFollowersList() {

    }

    private void initFailure() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
