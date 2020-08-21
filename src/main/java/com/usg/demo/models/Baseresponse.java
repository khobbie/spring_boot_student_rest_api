package com.usg.demo.models;

public class Baseresponse {

    private String responseCode;
    private String message;
    private Object data;

    public Baseresponse(String responseCode, String message, Object data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Baseresponse{" +
                "responseCode='" + responseCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
