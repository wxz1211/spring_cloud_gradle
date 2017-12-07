package com.example.exception;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -15:03
 */
public class ClientException extends Exception {
    private String message;

    public ClientException() {
    }

    public ClientException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
