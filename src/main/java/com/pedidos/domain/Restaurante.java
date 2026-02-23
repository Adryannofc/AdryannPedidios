package com.pedidos.domain;

import com.pedidos.application.CardapioService;

import java.util.List;
import java.util.ArrayList;

public class Restaurante extends Usuario {
    private List<Prato> cardapio;
    public Restaurante(String nome, String email, String senha) {
        super(nome, email, senha);
    }

   public CardapioService getCardapioService() {
        return new CardapioService();
   }

}
