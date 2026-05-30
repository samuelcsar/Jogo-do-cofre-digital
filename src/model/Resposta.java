package model;

import java.io.Serializable;

public class Resposta implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mensagem;

    public Resposta(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
