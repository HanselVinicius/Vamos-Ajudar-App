package br.got.vamosajudar.infra.exceptions;

public class ForbiddenException extends Exception {

    private int code;

    public ForbiddenException(String msg,int code) {
        super(msg);
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
