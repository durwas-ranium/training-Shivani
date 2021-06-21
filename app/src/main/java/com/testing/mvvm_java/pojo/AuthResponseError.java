package com.testing.mvvm_java.pojo;

import com.google.gson.annotations.SerializedName;

public class AuthResponseError {

    @SerializedName("error")
    public String error;
    @SerializedName("error_description")
    public String errorDescription;
    @SerializedName("message")
    public String message;

    public AuthResponseError(String error, String errorDescription, String message) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
