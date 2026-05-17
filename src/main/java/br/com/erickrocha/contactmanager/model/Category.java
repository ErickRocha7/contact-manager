package br.com.erickrocha.contactmanager.model;

/**
 * Enum que representa as categorias de um grupo.
 * Demonstra o uso de tipos enumerados (capítulos 6 e 8).
 */
public enum Category {
    FAMILY("Família"),
    WORK("Trabalho"),
    FRIENDS("Amigos");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}