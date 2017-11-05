package com.example.busroute.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dasari on 05/11/17.
 */

public interface RoutesAPI {

    @GET("v2/{id}")
    Observable<RouteModel> getRoutes(@Path("id") String id);
}
