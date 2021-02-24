package com.lxc.base.base.exception;

import com.lxc.base.constant.ResponseCode;

/**
 * @author lxc
 */
public class BaseException extends RuntimeException {

    private int errorCode = ResponseCode.CODE_UNKNOWN;
    private String errorMsg = "";

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
