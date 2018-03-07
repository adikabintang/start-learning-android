package com.mycompany.weleh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mycompany.weleh.data.model.Employee;
import com.mycompany.weleh.data.remote.APIService;
import com.mycompany.weleh.data.remote.APIUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CheckDataActivity extends AppCompatActivity {
    private APIService _apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_data);

        Intent intent = getIntent();
        String employeeIdStr = intent.getStringExtra(MainActivity.EMPLOYEE_KEY);

        TextView employeeIdDisplayTV = (TextView) findViewById(R.id.check_data_employee_id);
        employeeIdDisplayTV.setText(employeeIdStr);

        _apiService = APIUtils.getAPIService();
        sendEmployeeId(employeeIdStr);
    }

    protected void sendEmployeeId(String employeeId) {
        Employee employee = new Employee();

        employee.setEmployeeId(employeeId);

        _apiService.getEmployeeDetails(employee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Employee>() {
                    @Override
                    public void onNext(Employee employee) {
                        TextView employeeNameView = (TextView) findViewById(R.id.employeeName);
                        TextView employeeJobView = (TextView) findViewById(R.id.employeeJob);

                        employeeNameView.setText(employee.getEmployeeName());
                        employeeJobView.setText(employee.getEmployeeJob());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("OOOOI", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
