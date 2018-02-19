package com.mycompany.mygetapplication.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Working {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobName")
    @Expose
    private String jobName;
    @SerializedName("jobDesc")
    @Expose
    private String jobDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

}
