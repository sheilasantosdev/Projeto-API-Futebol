package com.api.projeto_final.controller.advice;

public class ExceptionPersonalizada extends RuntimeException {


    private int status;
    private String msg;

    public ExceptionPersonalizada(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
