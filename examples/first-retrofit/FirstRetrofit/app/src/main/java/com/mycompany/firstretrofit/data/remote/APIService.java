package com.mycompany.firstretrofit.data.remote;

import com.mycompany.firstretrofit.data.model.Post;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by bintang on 17/02/18.
 */

public interface APIService {
    // with standard way
    /*
    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(
            @Field("title") String title,
            @Field("body") String body,
            @Field("userId") String userId
    );
    */

    //with rxjava
    @POST("/posts")
    @FormUrlEncoded
    Observable<Post> savePost(@Field("title") String title,
                              @Field("body") String body,
                              @Field("userId") String userId);

    @POST("/posts")
    Observable<Post> savePost(@Body Post post);

}
