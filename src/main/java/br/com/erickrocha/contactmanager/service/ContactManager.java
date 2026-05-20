package br.com.erickrocha.contactmanager.service;

import br.com.erickrocha.contactmanager.model.Contact;
import br.com.erickrocha.contactmanager.model.Searchable;

import java.util.*;

/**
 * Classe responsável pelo gerenciamento dos contatos.
 * Possui operações de cadastro, remoção, busca,
 * ordenação e agrupamento de dados.
 */
public class ContactManager {

    // Lista principal que armazena os contatos cadastrados
    private ArrayList<Contact> contacts;

    /**
     * Construtor da classe.
     * Inicializa a lista de contatos.
     */
    public ContactManager() {
        contacts = new ArrayList<>();
    }

    /**
     * Adiciona múltiplos contatos utilizando varargs.
     *
     * @param novos contatos a serem adicionados
     */
    public void addContacts(Contact... novos) {

        // Percorre todos os contatos recebidos
        for (Contact c : novos) {

            // Adiciona cada contato à lista
            contacts.add(c);
        }
    }

    /**
     * Adiciona um único contato.
     *
     * @param c contato a ser adicionado
     */
    public void addContact(Contact c) {
        contacts.add(c);
    }

    /**
     * Remove um contato pelo índice informado.
     *
     * @param index posição do contato na lista
     * @throws IndexOutOfBoundsException caso o índice seja inválido
     */
    public void removeContact(int index) throws IndexOutOfBoundsException {

        // Valida se o índice existe na lista
        if (index < 0 || index >= contacts.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido para remoção.");
        }

        // Remove o contato da lista
        Contact removido = contacts.remove(index);

        // Exibe mensagem de confirmação
        System.out.printf(
                "Removido: %s (Total de instâncias: %d)%n",
                removido.getName(),
                Contact.instanceCount);

        // Atualiza contador estático de instâncias
        Contact.instanceCount--;
    }

    /**
     * Retorna a lista de contatos.
     *
     * @return lista de contatos
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Realiza busca utilizando polimorfismo através da interface Searchable.
     *
     * @param item  objeto pesquisável
     * @param query termo da busca
     */
    public void searchAndPrint(Searchable item, String query) {

        // Verifica se o objeto corresponde ao termo pesquisado
        if (item.matches(query)) {

            System.out.println("Encontrado: " + item);

        } else {

            System.out.println("Não corresponde à busca: " + query);
        }
    }

    /**
     * Busca o termo informado em todos os contatos cadastrados.
     *
     * @param query termo de busca
     */
    public void searchAll(String query) {

        System.out.println("Resultados para \"" + query + "\":");

        // Percorre toda a lista de contatos
        for (Contact c : contacts) {

            // Verifica se o contato atende ao critério de busca
            if (c.matches(query)) {

                System.out.println(" - " + c);
            }
        }
    }

    /**
     * Ordena a lista de contatos alfabeticamente pelo nome.
     */
    public void sortByName() {

        // Utiliza Collections.sort com Comparator anônimo
        Collections.sort(contacts, new Comparator<Contact>() {

            @Override
            public int compare(Contact c1, Contact c2) {

                // Comparação ignorando letras maiúsculas/minúsculas
                return c1.getName()
                        .compareToIgnoreCase(c2.getName());
            }
        });
    }

    /**
     * Método genérico para impressão de listas.
     *
     * @param lista lista de qualquer tipo
     * @param <T>   tipo genérico da lista
     */
    public static <T> void printList(List<T> lista) {

        // Percorre todos os elementos da lista
        for (T item : lista) {

            System.out.println(item);
        }
    }

    /**
     * Exibe todos os contatos cadastrados.
     */
    public void showAll() {

        // Verifica se a lista está vazia
        if (contacts.isEmpty()) {

            System.out.println("Nenhum contato cadastrado.");

        } else {

            System.out.println("--- Lista de Contatos ---");

            // Imprime todos os contatos
            printList(contacts);

            // Exibe total de registros
            System.out.printf("Total: %d%n", contacts.size());
        }
    }

    /**
     * Agrupa os contatos por domínio do e-mail.
     */
    public void showByDomain() {

        // Mapa onde:
        // chave = domínio do email
        // valor = lista de contatos daquele domínio
        Map<String, List<Contact>> map = new HashMap<>();

        // Percorre todos os contatos
        for (Contact c : contacts) {

            String email = c.getEmail();

            // Valor padrão caso não exista domínio válido
            String domain = "desconhecido";

            // Obtém posição do caractere '@'
            int atIndex = email.indexOf('@');

            // Valida se o email possui domínio
            if (atIndex >= 0 && atIndex < email.length() - 1) {

                // Extrai domínio após o '@'
                domain = email.substring(atIndex + 1)
                        .toLowerCase();
            }

            // Cria lista vazia caso o domínio ainda não exista no mapa
            map.putIfAbsent(domain, new ArrayList<>());

            // Adiciona contato ao grupo correspondente
            map.get(domain).add(c);
        }

        System.out.println(
                "--- Contatos agrupados por domínio de email ---");

        // Percorre cada grupo do mapa
        for (Map.Entry<String, List<Contact>> entry : map.entrySet()) {

            // Exibe o domínio
            System.out.println("Domínio: " + entry.getKey());

            // Exibe os contatos daquele domínio
            for (Contact c : entry.getValue()) {

                System.out.printf(
                        "   • %s (%s)%n",
                        c.getName(),
                        c.getEmail());
            }
        }
    }
}