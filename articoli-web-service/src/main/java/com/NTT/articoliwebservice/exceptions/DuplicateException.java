package com.NTT.articoliwebservice.exceptions;

public class DuplicateException extends Exception {
    private String messaggio;

    public DuplicateException(String Messaggio) {
        super(Messaggio);
        this.messaggio = Messaggio;
    }

    public DuplicateException() {
        super();
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
