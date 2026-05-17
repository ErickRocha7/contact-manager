package br.com.erickrocha.contactmanager.service;

import br.com.erickrocha.contactmanager.model.Contact;
import br.com.erickrocha.contactmanager.model.Searchable;

import java.util.*;

/**
 * Gerencia uma lista de contatos, oferecendo operações de adição, remoção,
 * busca, ordenação e agrupamento. Utiliza coleções genéricas e métodos
 * utilitários.
 */
public class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContacts(Contact... novos) {
        for (Contact c : novos) {
            contacts.add(c);
        }
    }

    public void addContact(Contact c) {
        contacts.add(c);
    }

    public void removeContact(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= contacts.size()) {
            throw new IndexOutOfBoundsException("Índice inválido para remoção.");
        }
        Contact removido = contacts.remove(index);
        System.out.printf("Removido: %s (Total de instâncias: %d)%n",
                removido.getName(), Contact.instanceCount);
        Contact.instanceCount--;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void searchAndPrint(Searchable item, String query) {
        if (item.matches(query)) {
            System.out.println("Encontrado: " + item);
        } else {
            System.out.println("Não corresponde à busca: " + query);
        }
    }

    public void searchAll(String query) {
        System.out.println("Resultados para \"" + query + "\":");
        for (Contact c : contacts) {
            if (c.matches(query)) {
                System.out.println(" - " + c);
            }
        }
    }

    public void sortByName() {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });
    }

    public static <T> void printList(List<T> lista) {
        for (T item : lista) {
            System.out.println(item);
        }
    }

    public void showAll() {
        if (contacts.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            System.out.println("--- Lista de Contatos ---");
            printList(contacts);
            System.out.printf("Total: %d%n", contacts.size());
        }
    }

    public void showByDomain() {
        Map<String, List<Contact>> map = new HashMap<>();

        for (Contact c : contacts) {
            String email = c.getEmail();
            String domain = "desconhecido";
            int atIndex = email.indexOf('@');
            if (atIndex >= 0 && atIndex < email.length() - 1) {
                domain = email.substring(atIndex + 1).toLowerCase();
            }
            map.putIfAbsent(domain, new ArrayList<>());
            map.get(domain).add(c);
        }

        System.out.println("--- Contatos agrupados por domínio de email ---");
        for (Map.Entry<String, List<Contact>> entry : map.entrySet()) {
            System.out.println("Domínio: " + entry.getKey());
            for (Contact c : entry.getValue()) {
                System.out.printf("   • %s (%s)%n", c.getName(), c.getEmail());
            }
        }
    }
}