package model;

import java.io.Serializable;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private int aposta;

    public Pedido(String nome, int aposta) {
        this.nome = nome;
        this.aposta = aposta;
    }

    public String getNome() {
        return nome;
    }

    public int getAposta() {
        return aposta;
    }
}
