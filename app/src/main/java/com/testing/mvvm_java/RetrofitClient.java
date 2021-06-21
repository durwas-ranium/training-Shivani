package com.testing.mvvm_java;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static Retrofit retrofit = null;


    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMGVlNGJmNDE4MTFmOWY4ZjQ5MzQ2YTU4NGNkYzczNmZmNjI1MTcyNjI0ZmVlYzE1YjNhYzE1OTg2NzQxNDZjZDkwYTkxZTc0Y2Q5MGI3MGEiLCJpYXQiOjE2MjM5MTM0MDUsIm5iZiI6MTYyMzkxMzQwNSwiZXhwIjoxNjU1NDQ5NDA1LCJzdWIiOiIyOCIsInNjb3BlcyI6W119.f0C_sgPHhHl69jzjSRMAVVsfmBly3rk_YsZcw0FG0iqOWNaDPor5sx2QvtFHCtkF8sn1hCT8pSHshQb1ThE5ppfmDOoBkxVzpfqQAXrLYC-zd21HFocLu9ujDwbRmvHbtvMevaX10Opec5V8u16zn_QsZ3GJePTBjAbLZhsmjD-bY3q5Bj-FtaBZcmtmkZIl6GodV9IvwiXG6aqDja0aJITpoxlBs1ug88whH_1Eeq1TA8CYHO-7VJwTZyMM6JmTzff92OiTgZ15vTTUyipIYtnRYG2fpZNUdV6GaT5N1yG5i1dcLy-380kdMBjtzIqzyPoh1Gx7btITx1PaTz40_VEyBQTZE4GAGlHxObhXQgOH2vpwoadLtujv-i7fACdzFsmSHp2N0zbSza0qG6lEBSJvMVC1jELQP6FfJrw1xsECoolIREAjhUg19PUgbhwfu1SmJ0eO4SFURM0B6H91AEnNiv2sYM2K8zNpNJnmU8hGeUaSR1aO2x8-p3IO80jNx5N0whhmk6Sq21WDUcXmmSlRVHTArKUjXRsFOx9DuQwqDmudeB2rcWsc3ACvIJfwZsm4mQAaEDEKOlKt6g8i6A3FfKXjtBJmrBntn34qmBs_00VOnAsOeFxZn849DWENLbzZ5g_n7Sjgb92Jgxr2YNEL8tfL-WWP6rcd4rt-3rE";


    public Retrofit provideRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
         //.baseUrl(Constants.Host)
                .baseUrl("https://develop.empeq.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public Retrofit provideRetrofitWithHeader(Context context){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                System.out.println(">>>>>> response access token " + SessionManager.getInstance(context).getAccessToken());
                Request original = chain.request();
                Request request = original.newBuilder()
                        .addHeader("Content-Type","application/json")
                        .addHeader("Authorization","Bearer "+ token)
                        .method(original.method(),original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                // .baseUrl(Constants.Host)
                .baseUrl("https://develop.empeq.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;

    }

    public Retrofit getClient(Map<String, String> headerMap) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constants.Host);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        if (headerMap != null) {
            builder.client(getHeader(headerMap));
        } else {
            okhttp3.logging.HttpLoggingInterceptor interceptor = new okhttp3.logging.HttpLoggingInterceptor()
                    .setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.callTimeout(2, TimeUnit.MINUTES);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30,TimeUnit.SECONDS);
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG)
                httpClient.addInterceptor(interceptor);
            builder.client(httpClient.build());
        }
        retrofit = builder.build();
        return retrofit;

    }

    @NonNull
    private static OkHttpClient getHeader(final Map<String, String> header) {

        okhttp3.logging.HttpLoggingInterceptor interceptor = new okhttp3.logging.HttpLoggingInterceptor()
                .setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder();
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
                builder.method(original.method(), original.body());
                return chain.proceed(builder.build());
            }
        });
        if (BuildConfig.DEBUG) //hides exposing web service call in logcat when app is released
            httpClient.addInterceptor(interceptor);
        return httpClient.build();

    }


}
