package com.pedidos.application;

import com.pedidos.domain.Prato;

import java.util.ArrayList;
import java.util.List;

// Este serviço pode ser responsável por gerenciar o cardápio do restaurante, incluindo a adição, remoção e atualização de pratos.
// Ele pode interagir com um repositório de pratos para armazenar e recuperar as informações do cardápio.
public class CardapioService {
    private List<Prato> cardapio;
    private int proximoId = 1;

    public CardapioService() {
        cardapio = new ArrayList<>();
        cardapio.add(new Prato(proximoId++, "Hambúrguer", "Hambúrguer com queijo, alface e tomate", 15.00, true));
        cardapio.add(new Prato(proximoId++, "Pizza Margherita", "Pizza com molho de tomate, mussarela e manjericão", 35.00, true));
        cardapio.add(new Prato(proximoId++, "Frango Grelhado", "Frango grelhado com legumes e arroz", 22.00, true));
        cardapio.add(new Prato(proximoId++, "Salada Caesar", "Salada com frango, croutons e molho caesar", 18.00, true));
        cardapio.add(new Prato(proximoId++, "Batata Frita", "Porção de batata frita crocante", 12.00, true));
        cardapio.add(new Prato(proximoId++, "Refrigerante", "Lata 350ml", 6.00, true));
    }

    public Prato adicionarPrato(String nome, String descricao, double preco) {
        Prato prato = new Prato(proximoId++, nome, descricao, preco, true);
        cardapio.add(prato);
        return prato;
    }

    public boolean removerPrato(int id) {
        return cardapio.removeIf(prato -> prato.getId() == id);
    }

    public boolean editarPrato(int id, String nome, String descricao, double preco) {
        for (Prato prato : cardapio) {
            if (prato.getId() == id) {
                prato.setNome(nome);
                prato.setDescricao(descricao);
                prato.setPreco(preco);
                return true;
            }
        }
        return false;
    }

    public List<Prato> getCardapio() {
        return cardapio;
    }
}
