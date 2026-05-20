package br.com.erickrocha.contactmanager.model;

/**
 * Enum que representa as categorias disponíveis
 * para os grupos de contatos.
 *
 * Utiliza tipos enumerados para garantir
 * valores fixos e controlados no sistema.
 */
public enum Category {

    // Categoria de familiares
    FAMILY("Família"),

    // Categoria profissional
    WORK("Trabalho"),

    // Categoria de amizades
    FRIENDS("Amigos");

    // Nome amigável exibido ao usuário
    private final String displayName;

    /**
     * Construtor do enum.
     *
     * @param displayName nome exibido da categoria
     */
    Category(String displayName) {

        this.displayName = displayName;
    }

    /**
     * Retorna o nome amigável da categoria.
     *
     * @return nome de exibição
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Representação textual da categoria.
     *
     * @return nome amigável da categoria
     */
    @Override
    public String toString() {
        return displayName;
    }
}