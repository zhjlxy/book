package com.book.bo;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by lixuy on 2016/4/23.
 */
public class Message {
    private Status status;
    private String statusMsg = "";
    private ArrayList<Integer> errorKys;
    private String error = "";
    private String data= "{}";

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public ArrayList<Integer> getErrorKys() {
        return errorKys;
    }

    public void setErrorKys(ArrayList<Integer> errorKys) {
        this.errorKys = errorKys;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
