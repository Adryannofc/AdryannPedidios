package com.pedidos.domain;

public abstract class Usuario {
    private  String email;
    private String senha;
    private String nome;

    public Usuario(String nome, String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
