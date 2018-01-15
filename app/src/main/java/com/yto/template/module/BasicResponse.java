package com.yto.template.module;

/**
 * Created by Chris on 2017/11/30.
 */

public class BasicResponse<T> {
    private String code;
    private String errmsg;
    private T data;
//    private boolean error;

    public T getResults() {
        return data;
    }

    public void setResults(T results) {
        this.data = results;
    }

//    public boolean isError() {
//        return error;
//    }
//
//    public void setError(boolean error) {
//        this.error = error;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return errmsg;
    }

    public void setMessage(String message) {
        this.errmsg = message;
    }
}
