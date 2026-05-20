package br.com.erickrocha.contactmanager;

import br.com.erickrocha.contactmanager.model.Contact;
import br.com.erickrocha.contactmanager.model.Group;
import br.com.erickrocha.contactmanager.model.Category;
import br.com.erickrocha.contactmanager.exception.InvalidContactException;
import br.com.erickrocha.contactmanager.service.ContactManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principal do sistema de gerenciamento de contatos.
 * Responsável pelo menu principal e fluxo de interação com o usuário.
 */
public class ContactManagerApplication {

    /**
     * Método principal da aplicação.
     */
    public static void main(String[] args) {

        // try-with-resources fecha automaticamente o Scanner ao final da execução
        try (Scanner scanner = new Scanner(System.in)) {

            // Instância responsável pela lógica de gerenciamento de contatos
            ContactManager manager = new ContactManager();

            int option = 0;

            // Monta o menu utilizando StringBuilder para melhor performance
            StringBuilder menuBuilder = new StringBuilder();
            menuBuilder.append("\n===== CONTACT MANAGER =====\n");
            menuBuilder.append("1. Adicionar contato\n");
            menuBuilder.append("2. Listar contatos\n");
            menuBuilder.append("3. Buscar contatos\n");
            menuBuilder.append("4. Remover contato (por índice)\n");
            menuBuilder.append("5. Ordenar por nome\n");
            menuBuilder.append("6. Agrupar por domínio de email\n");
            menuBuilder.append("0. Sair\n");
            menuBuilder.append("Escolha: ");

            // Converte o conteúdo do StringBuilder para String
            String menu = menuBuilder.toString();

            // Loop principal do sistema
            while (true) {

                // Exibe o menu
                System.out.print(menu);

                try {

                    // Lê a opção digitada pelo usuário
                    option = scanner.nextInt();

                    // Consome a quebra de linha pendente do buffer
                    scanner.nextLine();

                    // Validação de faixa permitida
                    if (option < 0 || option > 6) {
                        System.out.println("Opção inválida! Use 0-6.");
                        continue;
                    }

                    // Executa a ação correspondente à opção escolhida
                    switch (option) {

                        case 0:

                            String confirm;

                            // Solicita confirmação antes de encerrar o sistema
                            do {
                                System.out.print("Tem certeza que deseja sair? (s/n): ");
                                confirm = scanner.nextLine().trim().toLowerCase();

                            } while (!confirm.equals("s") && !confirm.equals("n"));

                            // Finaliza a aplicação
                            if (confirm.equals("s")) {
                                System.out.println("Encerrando...");
                                return;
                            }

                            break;

                        case 1:

                            // Fluxo de cadastro de contato
                            addContactFlow(manager, scanner);
                            break;

                        case 2:

                            // Exibe todos os contatos
                            manager.showAll();
                            break;

                        case 3:

                            // Fluxo de busca
                            searchFlow(manager, scanner);
                            break;

                        case 4:

                            // Fluxo de remoção
                            removeFlow(manager, scanner);
                            break;

                        case 5:

                            // Ordena contatos pelo nome
                            manager.sortByName();

                            System.out.println("Lista ordenada por nome.");
                            break;

                        case 6:

                            // Exibe agrupamento por domínio de e-mail
                            manager.showByDomain();
                            break;
                    }

                } catch (InputMismatchException e) {

                    // Trata entradas inválidas no menu
                    System.out.println("Entrada inválida! Digite um número.");

                    // Limpa o buffer do Scanner
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * Fluxo responsável pela criação de novos contatos.
     */
    private static void addContactFlow(ContactManager manager, Scanner scanner) {

        try {

            // Solicita os dados do contato
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Telefone (apenas dígitos, 10-11): ");
            String phone = scanner.nextLine();

            // Cria o objeto contato
            Contact novo = new Contact(name, email, phone);

            // Adiciona o contato ao gerenciador
            manager.addContact(novo);

            // Exibe quantidade total de instâncias criadas
            System.out.printf(
                    "Contato adicionado com sucesso! Total de instâncias: %d%n",
                    Contact.instanceCount);

        } catch (InvalidContactException e) {

            // Trata erros de validação do contato
            System.out.println("Erro ao criar contato: " + e.getMessage());

        } catch (Exception e) {

            // Trata erros genéricos inesperados
            System.out.println("Erro inesperado: " + e.getMessage());

        } finally {

            // Executa independentemente de sucesso ou erro
            System.out.println("Operação de adição finalizada.");
        }
    }

    /**
     * Fluxo responsável pela busca de contatos.
     */
    private static void searchFlow(ContactManager manager, Scanner scanner) {

        // Solicita o termo de busca
        System.out.print("Termo de busca: ");

        String query = scanner.nextLine();

        // Busca em todos os contatos
        manager.searchAll(query);

        // Caso existam contatos cadastrados
        if (!manager.getContacts().isEmpty()) {

            // Obtém o primeiro contato da lista
            Contact primeiro = manager.getContacts().get(0);

            System.out.println("Verificando primeiro contato individualmente:");

            // Realiza busca individual utilizando polimorfismo
            manager.searchAndPrint(primeiro, query);
        }

        // Cria um grupo fictício para demonstrar busca em outro tipo de objeto
        Group grupo = new Group(
                "Trabalho",
                "Colegas do escritório",
                Category.WORK);

        System.out.println("Verificando um grupo:");

        // Busca no grupo
        manager.searchAndPrint(grupo, query);
    }

    /**
     * Fluxo responsável pela remoção de contatos.
     */
    private static void removeFlow(ContactManager manager, Scanner scanner) {

        // Exibe todos os contatos antes da remoção
        manager.showAll();

        // Se não houver contatos cadastrados, encerra o método
        if (manager.getContacts().isEmpty())
            return;

        System.out.print("Índice do contato a remover: ");

        try {

            // Lê o índice informado pelo usuário
            int index = scanner.nextInt();

            // Limpa quebra de linha pendente
            scanner.nextLine();

            // Remove o contato pelo índice
            manager.removeContact(index);

        } catch (IndexOutOfBoundsException e) {

            // Trata índice inexistente
            System.out.println("Erro: " + e.getMessage());

        } catch (InputMismatchException e) {

            // Trata entradas não numéricas
            System.out.println("Índice inválido.");

            // Limpa o buffer
            scanner.nextLine();

        } finally {

            // Executa sempre ao final do fluxo
            System.out.println("Tentativa de remoção concluída.");
        }
    }
}