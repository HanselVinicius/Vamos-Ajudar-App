package br.got.vamosajudar.infra.exceptions;

public class QrCodeException extends RuntimeException{


    public QrCodeException(String message,Throwable cause) {
        super(message,cause);
    }
    public QrCodeException(String message) {
        super(message);
    }

}
