package com.mycompany.weleh.data.remote;

import com.mycompany.weleh.data.model.Employee;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("/employee")
    Observable<Employee> getEmployeeDetails(@Body Employee employee);
}
