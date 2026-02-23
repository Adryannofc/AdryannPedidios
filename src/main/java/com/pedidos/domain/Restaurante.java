package com.pedidos.domain;

import com.pedidos.application.CardapioService;

import java.util.List;
import java.util.ArrayList;

public class Restaurante extends Usuario {
    private CardapioService cardapioService;
    private String cnpj;

    public Restaurante(String nome, String email, String senha, String cnpj) {
        super(nome, email, senha);
        this.cnpj = cnpj;
        cardapioService = new CardapioService();
    }


    public CardapioService getCardapioService() {
        return cardapioService;
    }

    public String getCnpj() {
        return cnpj;
    }
}
