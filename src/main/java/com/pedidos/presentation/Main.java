package com.pedidos.presentation;

import com.pedidos.domain.Usuario;
import com.pedidos.domain.Cliente;
import com.pedidos.domain.Restaurante;
import com.pedidos.application.UsuarioService;

import java.util.Scanner;

public class Main {
    private static Scanner scan =  new Scanner(System.in);
    private static UsuarioService service = new UsuarioService();

    public static void main(String[] args) {
        Usuario usuarioLogado = telaLogin();

        // instanceof: Esse objeto foi criado a partir dessa classe? se sim abre o menu respectivo
        if(usuarioLogado != null) {
            if(usuarioLogado instanceof Restaurante) {
                menuRestaurante();
            } else if (usuarioLogado instanceof Cliente) {
                menuCliente();
            }
        } else {
            System.out.println("Saindo do sistema...");
        }
    }

    static Usuario telaLogin() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                   LOGIN                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("E-MAIL: ");
        String email = scan.nextLine();

        System.out.print("SENHA: ");
        String senha = scan.nextLine();

        Usuario user = service.fazerLogin(email, senha);

        if(user == null) {
            System.out.println("❌ Credenciais inválidas!");
        }
        return user;
    }

    static void menuRestaurante() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              MENU RESTAURANTE            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("[1] Gerenciar Cardápio");
        System.out.println("[2] Exibir Pedidos Recebidos");
        System.out.println("[9] Configuracoes");
        System.out.println("[0] Sair");
    }

    static void menuCliente() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                MENU CLIENTE              ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("[1] Exibir Restaurantes");
        System.out.println("[9] Configuracoes");
        System.out.println("[0] Sair");
    }

    static void limparTela() {
        for(int i = 0; i < 20; i++){
            System.out.println("");
        }
    }
}
