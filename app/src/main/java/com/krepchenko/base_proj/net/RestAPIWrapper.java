package com.krepchenko.base_proj.net;

import com.krepchenko.base_proj.BuildConfig;
import com.krepchenko.base_proj.utils.Preferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ann on 29.08.2016.
 */
public class RestAPIWrapper {

    private static RestAPIWrapper instance = null;

    private RestAPI restApi;

    public static RestAPIWrapper getInstance() {
        if (instance == null) {
            instance = new RestAPIWrapper();
        }
        return instance;
    }

    public RestAPIWrapper() {
        restApi = retrofit.create(RestAPI.class);
    }

    public OkHttpClient initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        httpClient.readTimeout(5, TimeUnit.MINUTES);
        httpClient.connectTimeout(5, TimeUnit.MINUTES);
        httpClient.writeTimeout(5, TimeUnit.MINUTES);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", Preferences.getInstance().getAuthorizationToken())
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    Retrofit retrofit = new Retrofit.Builder()
            .client(initOkHttp())
            .baseUrl(BuildConfig.SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
