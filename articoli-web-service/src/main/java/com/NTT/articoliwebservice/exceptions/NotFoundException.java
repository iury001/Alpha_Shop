package com.NTT.articoliwebservice.exceptions;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = -8729169303699924451L;

    private String messaggio = "Elemento ricercato non trovato!";

    public NotFoundException() {
        super();
    }

    public NotFoundException(String Messaggio) {
        super(Messaggio);
        this.messaggio = Messaggio;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
