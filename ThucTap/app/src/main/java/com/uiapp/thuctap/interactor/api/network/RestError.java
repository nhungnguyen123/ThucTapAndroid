package com.uiapp.thuctap.interactor.api.network;

import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.R;

import java.io.Serializable;

/**
 * Created by hongnhung on 7/6/16.
 */
public class RestError implements Serializable {

//    @SerializedName("success")
    public boolean success;

//    @SerializedName("code")
    public int code;

//    @SerializedName("message")
    public String message;

    public RestError(int code) {
        this.code = code;
        this.message= getErrorMessage(code);
    }

    public RestError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final int API_ERROR_NO_NETWORK = -1;
    public static final int API_ERROR_TIMED_OUT = -2;
    public static final int API_ERROR_UNAUTHORIZED = -3;
    public static final int API_ERROR_UNKNOWN = -4;
    public static final int API_ERROR_NOT_FOUND = -5;
    public static final int API_ERROR_SERVER_ERROR = -6;

    private String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case API_ERROR_NO_NETWORK:
                return MainApplication.getContext().getString(R.string.api_err_no_network);
            case API_ERROR_TIMED_OUT:
                return MainApplication.getContext().getString(R.string.api_err_time_out);
            case API_ERROR_NOT_FOUND:
                return MainApplication.getContext().getString(R.string.api_err_not_found);
            case API_ERROR_UNAUTHORIZED:
                return MainApplication.getContext().getString(R.string.api_err_unauthorized_error);
            case API_ERROR_UNKNOWN:
                return MainApplication.getContext().getString(R.string.api_err_unknown_error);
            case API_ERROR_SERVER_ERROR:
                return MainApplication.getContext().getString(R.string.api_err_server_error);

        }
        return "";
    }

}
