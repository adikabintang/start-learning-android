package com.mycompany.weleh.data.remote;

import static com.mycompany.weleh.BuildConfig.BASE_URL;

public class APIUtils {
    private APIUtils() {}

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
