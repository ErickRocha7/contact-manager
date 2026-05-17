package br.com.erickrocha.contactmanager.model;

/**
 * Contrato que define a capacidade de buscar por um termo.
 * É implementado por entidades do domínio como Contact e Group.
 */
public interface Searchable {
    boolean matches(String query);
}