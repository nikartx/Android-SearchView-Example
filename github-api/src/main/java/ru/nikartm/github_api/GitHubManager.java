package ru.nikartm.github_api;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import ru.nikartm.github_api.model.Follower;

/**
 * Manager of GitHub requests
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class GitHubManager {

    private static GitHubManager instance;
    private static ApiService service;

    private GitHubManager() {}

    /**
     * Get GitHubManager instance
     */
    public static synchronized GitHubManager getInstance(Context context) {
        if(instance == null) {
            instance = new GitHubManager();
            service = ApiBuilder.build(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Get Follower list
     * @return {@link Flowable} follower list
     */
    public Flowable<List<Follower>> getFollowers(String username) {
        return service.getFollowers(username);
    }

}
