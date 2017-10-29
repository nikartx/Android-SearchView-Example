package ru.nikartm.github_api;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nikartm.github_api.exception.NoNetworkException;
import ru.nikartm.github_api.util.NetUtils;

/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
class ApiBuilder {

    private static Retrofit retrofit = null;

    /**
     * Configure api service instance
     * @return api service instance
     */
    static ApiService build(Context context) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.base_url))
                    .client(getClient(context.getApplicationContext()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

    private static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    if (NetUtils.isNetworkAvailable(context)) {
                        return chain.proceed(chain.request());
                    } else {
                        throw new NoNetworkException("No network connection");
                    }
                })
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/vnd.github.v3+json");
                    return chain.proceed(requestBuilder.build());
                });
        return httpClient.build();
    }
}
