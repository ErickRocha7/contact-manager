package br.com.erickrocha.contactmanager.model;

/**
 * Representa um grupo de contatos. Implementa Searchable para demonstrar
 * polimorfismo com um tipo diferente de Contact. Utiliza o enum Category.
 */
public class Group implements Searchable {
    private String name;
    private String description;
    private Category category;

    public Group(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean matches(String query) {
        String lowerQuery = query.toLowerCase();
        return name.toLowerCase().contains(lowerQuery) ||
                description.toLowerCase().contains(lowerQuery) ||
                category.getDisplayName().toLowerCase().contains(lowerQuery);
    }

    @Override
    public String toString() {
        return String.format("Grupo: %s [%s] - %s", name, category, description);
    }
}