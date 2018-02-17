package com.mycompany.firstretrofit.data.remote;

import static com.mycompany.firstretrofit.BuildConfig.BASE_URL;

/**
 * Created by bintang on 17/02/18.
 */

// Singleton class
public class ApiUtils {
    private ApiUtils() {}

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
