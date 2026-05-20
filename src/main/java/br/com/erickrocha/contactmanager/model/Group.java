package br.com.erickrocha.contactmanager.model;

/**
 * Classe que representa um grupo de contatos.
 *
 * Implementa a interface Searchable para permitir
 * buscas utilizando polimorfismo.
 *
 * Também utiliza o enum Category para categorizar
 * os grupos do sistema.
 */
public class Group implements Searchable {

    // Nome do grupo
    private String name;

    // Descrição do grupo
    private String description;

    // Categoria do grupo
    private Category category;

    /**
     * Construtor da classe.
     *
     * @param name        nome do grupo
     * @param description descrição do grupo
     * @param category    categoria do grupo
     */
    public Group(String name, String description, Category category) {

        this.name = name;
        this.description = description;
        this.category = category;
    }

    /**
     * Retorna a categoria do grupo.
     *
     * @return categoria
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Verifica se o grupo corresponde ao termo pesquisado.
     *
     * A busca é realizada:
     * - no nome do grupo
     * - na descrição
     * - no nome da categoria
     *
     * @param query termo da busca
     * @return true se encontrar correspondência
     */
    @Override
    public boolean matches(String query) {

        // Converte o termo para minúsculo para evitar
        // diferenças entre maiúsculas/minúsculas
        String lowerQuery = query.toLowerCase();

        return name.toLowerCase().contains(lowerQuery) ||

                description.toLowerCase().contains(lowerQuery) ||

                category.getDisplayName()
                        .toLowerCase()
                        .contains(lowerQuery);
    }

    /**
     * Representação textual do grupo.
     *
     * @return string formatada do grupo
     */
    @Override
    public String toString() {

        return String.format(
                "Grupo: %s [%s] - %s",
                name,
                category,
                description);
    }
}