package com.mycompany.mygetapplication.data.remote;

import static com.mycompany.mygetapplication.BuildConfig.BASE_URL;

public class ApiUtils {
    public static WorkingService getWorkingService() {
        return RetrofitClient.getClient(BASE_URL).create(WorkingService.class);
    }
}
