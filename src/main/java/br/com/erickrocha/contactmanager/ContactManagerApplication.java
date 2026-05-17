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
 * Contém o método main e o loop de interação com o usuário.
 */
public class ContactManagerApplication {
    public static void main(String[] args) {
        // try-with-resources garante fechamento automático do Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            ContactManager manager = new ContactManager();
            int option = 0;

            // Construção do menu com StringBuilder
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
            String menu = menuBuilder.toString();

            while (true) {
                System.out.print(menu);
                try {
                    option = scanner.nextInt();
                    scanner.nextLine(); // consumir quebra de linha

                    if (option < 0 || option > 6) {
                        System.out.println("Opção inválida! Use 0-6.");
                        continue;
                    }

                    switch (option) {
                        case 0:
                            String confirm;
                            do {
                                System.out.print("Tem certeza que deseja sair? (s/n): ");
                                confirm = scanner.nextLine().trim().toLowerCase();
                            } while (!confirm.equals("s") && !confirm.equals("n"));

                            if (confirm.equals("s")) {
                                System.out.println("Encerrando...");
                                return;
                            }
                            break;
                        case 1:
                            addContactFlow(manager, scanner);
                            break;
                        case 2:
                            manager.showAll();
                            break;
                        case 3:
                            searchFlow(manager, scanner);
                            break;
                        case 4:
                            removeFlow(manager, scanner);
                            break;
                        case 5:
                            manager.sortByName();
                            System.out.println("Lista ordenada por nome.");
                            break;
                        case 6:
                            manager.showByDomain();
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número.");
                    scanner.nextLine();
                }
            }
        }
    }

    private static void addContactFlow(ContactManager manager, Scanner scanner) {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Telefone (apenas dígitos, 10-11): ");
            String phone = scanner.nextLine();

            Contact novo = new Contact(name, email, phone);
            manager.addContact(novo);
            System.out.printf("Contato adicionado com sucesso! Total de instâncias: %d%n",
                    Contact.instanceCount);
        } catch (InvalidContactException e) {
            System.out.println("Erro ao criar contato: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            System.out.println("Operação de adição finalizada.");
        }
    }

    private static void searchFlow(ContactManager manager, Scanner scanner) {
        System.out.print("Termo de busca: ");
        String query = scanner.nextLine();
        manager.searchAll(query);

        if (!manager.getContacts().isEmpty()) {
            Contact primeiro = manager.getContacts().get(0);
            System.out.println("Verificando primeiro contato individualmente:");
            manager.searchAndPrint(primeiro, query);
        }

        Group grupo = new Group("Trabalho", "Colegas do escritório", Category.WORK);
        System.out.println("Verificando um grupo:");
        manager.searchAndPrint(grupo, query);
    }

    private static void removeFlow(ContactManager manager, Scanner scanner) {
        manager.showAll();
        if (manager.getContacts().isEmpty())
            return;
        System.out.print("Índice do contato a remover: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            manager.removeContact(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Índice inválido.");
            scanner.nextLine();
        } finally {
            System.out.println("Tentativa de remoção concluída.");
        }
    }
}