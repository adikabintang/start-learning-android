package com.mycompany.mygetapplication.data.remote;

import com.mycompany.mygetapplication.data.model.Working;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WorkingService {
    @GET("/working")
    Observable<Working> getMyJob();
}
