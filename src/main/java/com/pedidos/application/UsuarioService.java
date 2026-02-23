package com.pedidos.application;

import com.pedidos.domain.Cliente;
import com.pedidos.domain.Restaurante;
import com.pedidos.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuariosCadastrados;

    public UsuarioService() {
        usuariosCadastrados = new ArrayList<>();
        usuariosCadastrados.add(new Cliente("adryann", "adryann@gmail.com", "1234"));
        usuariosCadastrados.add(new Restaurante("burger", "burger@gmail.com", "4321", "123123123"));
    }

    public Usuario fazerLogin(String email, String senha) {
        for(Usuario usuario : usuariosCadastrados) {
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}
