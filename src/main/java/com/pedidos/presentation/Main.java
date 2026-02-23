package com.pedidos.presentation;

import com.pedidos.domain.Prato;
import com.pedidos.domain.Usuario;
import com.pedidos.domain.Cliente;
import com.pedidos.domain.Restaurante;
import com.pedidos.application.UsuarioService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static UsuarioService service = new UsuarioService();

    public static void main(String[] args) {
        Usuario usuarioLogado = telaLogin();

        redirecionarMenu(usuarioLogado);
    }

    static Usuario telaLogin() {
        limparTela();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                    LOGIN                                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.print("E-MAIL: ");
        String email = scan.nextLine();

        System.out.print("SENHA: ");
        String senha = scan.nextLine();

        Usuario user = service.fazerLogin(email, senha);

        if (user == null) {
            System.out.println("❌ Credenciais inválidas!");
            return telaLogin();
        } else {
            System.out.printf("Login realizado com sucesso! %s\n", user.getNome());
        }
        return user;
    }

    static void menuRestaurante(Restaurante restaurante) {
        limparTela();
        int opcao;
        do {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                              MENU RESTAURANTE                                ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("[1] Gerenciar Cardápio");
            System.out.println("[2] Exibir Pedidos Recebidos");
            System.out.println("[9] Configuracoes");
            System.out.println("[0] Sair");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    telaCardapio(restaurante);
                    break;
                case 2:
                    System.out.println("Exibindo Pedidos Recebidos");
                    break;
                case 9:
                    telaConfiguracoes(restaurante);
                    break;
                case 0:
                    redirecionarMenu(telaLogin());
                    break;
            }
        } while (opcao != 0);

    }

    static void telaCardapio(Restaurante restaurante) {
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            GERENCIAR CARDÁPIO                                ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  ID  │  NOME               │  DESCRIÇÃO                        │  PREÇO      ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

            for (Prato prato : restaurante.getCardapioService().getCardapio()) {
                String descricaoResumida = prato.getDescricao().length() > 33 ? prato.getDescricao().substring(0, 30) + "..." : prato.getDescricao();
                System.out.printf("║  %-3d │  %-18s │  %-33s │  R$%-6.2f  ║%n", prato.getId(), prato.getNome(), descricaoResumida, prato.getPreco());
            }
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [1] Adicionar   [2] Remover   [3] Editar   [0] Voltar                       ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("- Opcão: ");
            opcao = scan.nextInt();
            ;
            scan.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do prato: ");
                    String nome = scan.nextLine();

                    System.out.print("Descrição do prato: ");
                    String descricao = scan.nextLine();

                    System.out.print("Preço do prato: ");
                    double preco = scan.nextDouble();
                    scan.nextLine();

                    restaurante.getCardapioService().adicionarPrato(nome, descricao, preco);
                    break;
                case 2:
                    System.out.print("Digite o ID do prato a ser removido: ");
                    int idRemover = scan.nextInt();
                    scan.nextLine();
                    restaurante.getCardapioService().removerPrato(idRemover);
                    break;
                case 3:
                    System.out.println("Digite o ID do prato a ser editado: ");
                    int idEditado = scan.nextInt();
                    scan.nextLine();

                    Prato pratoEditar = restaurante.getCardapioService().buscarPrato(idEditado);

                    if (pratoEditar == null) {
                        System.out.println("❌ Prato não encontrado!");
                        break;
                    }

                    System.out.printf("Deseja MUDAR com o nome '%s'? (s/n): ", pratoEditar.getNome());
                    String novoNome = pratoEditar.getNome();
                    if (scan.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Novo nome: ");
                        novoNome = scan.nextLine();
                    }

                    System.out.printf("Deseja MUDAR com o descricao '%s'? (s/n):", pratoEditar.getDescricao());
                    String novoDescricao = pratoEditar.getDescricao();
                    if (scan.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Novo descricao: ");
                        novoDescricao = scan.nextLine();
                    }

                    System.out.printf("Deseja MUDAR com o preco '%.2f?' (s/n): ", pratoEditar.getPreco());
                    double novoPreco = pratoEditar.getPreco();
                    if (scan.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Novo preco: ");
                        novoPreco = scan.nextDouble();
                    }

                    restaurante.getCardapioService().editarPrato(idEditado, novoNome, novoDescricao, novoPreco);
                    break;
                case 0:
                    menuRestaurante(restaurante);
                    break;
            }

        } while (opcao != 0);
    }

    static void telaConfiguracoes(Restaurante restaurante) {
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            CONFIGURAÇÕES                                     ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║  Nome:   %-68s║%n", restaurante.getNome());
            System.out.printf("║  E-mail: %-68s║%n", restaurante.getEmail());
            System.out.printf("║  CNPJ:   %-68s║%n", restaurante.getCnpj());
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [1] Alterar Nome                                                            ║");
            System.out.println("║  [2] Alterar E-mail                                                          ║");
            System.out.println("║  [3] Alterar Senha                                                           ║");
            System.out.println("║  [0] Voltar                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("- Opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    String novoNome = scan.nextLine();
                    restaurante.setNome(novoNome);
                    break;
                case 2:
                    System.out.print("Novo E-mail:");
                    String novoEmail = scan.nextLine();
                    restaurante.setEmail(novoEmail);
                    break;
                case 3:
                    System.out.print("Novo Senha:");
                    String novoSenha = scan.nextLine();
                    restaurante.setSenha(novoSenha);
            }
        } while (opcao != 0);
    }

    static void menuCliente() {
        int opcao;
        do {
            limparTela();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                MENU CLIENTE                                  ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("[1] Exibir Restaurantes");
            System.out.println("[9] Configuracoes");
            System.out.println("[0] Voltar");
            opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Exibindo Restaurante");
                    break;
                case 2:
                    System.out.println("Exibindo Cliente");
                    break;
                case 0:
                    redirecionarMenu(telaLogin());
                    break;
            }
        } while (opcao != 0);

    }

    static void redirecionarMenu(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Saindo do sistema...");
            return;
        }

        if (usuario instanceof Restaurante r) {
            menuRestaurante(r);
        } else if (usuario instanceof Cliente) {
            menuCliente();
        }
    }

    static void limparTela() {
        for (int i = 0; i < 20; i++) {
            System.out.println("");
        }
    }
}
