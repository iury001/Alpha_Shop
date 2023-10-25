package com.NTT.articoliwebservice.exceptions;

public class BindingException extends Exception {
    private static final long serialVersionUID = -1646083143194195402L;

    private String messaggio;

    public BindingException() {
        super();
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public BindingException(String Messaggio) {
        super(Messaggio);
        this.messaggio = Messaggio;


    }
}
