package ru.nikartm.github_api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nikartm.github_api.model.Follower;

/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public interface ApiService {

    @GET("/users/{username}/followers")
    Flowable<List<Follower>> getFollowers(@Path("username") String username);
}
