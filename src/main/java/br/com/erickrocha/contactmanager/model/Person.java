package br.com.erickrocha.contactmanager.model;

/**
 * Classe abstrata que representa uma pessoa genérica.
 * Serve como superclasse para outras entidades do sistema.
 *
 * Possui regras de validação para garantir integridade
 * dos dados de nome e email.
 */
public abstract class Person {

    // Nome da pessoa
    protected String name;

    // Email da pessoa
    protected String email;

    /**
     * Construtor padrão.
     * Define valores default para evitar atributos nulos.
     */
    public Person() {

        this.name = "Sem nome";
        this.email = "sem@email.com";
    }

    /**
     * Construtor parametrizado.
     *
     * @param name  nome da pessoa
     * @param email email da pessoa
     */
    public Person(String name, String email) {

        // Utiliza setters para reaproveitar validações
        setName(name);
        setEmail(email);
    }

    /**
     * Método abstrato que deve ser implementado
     * pelas subclasses para identificar o tipo do objeto.
     *
     * @return tipo da entidade
     */
    public abstract String getType();

    /**
     * Retorna o nome da pessoa.
     *
     * @return nome
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da pessoa com validação.
     *
     * @param name nome informado
     */
    public void setName(String name) {

        // Valida se o nome é nulo ou vazio
        if (name == null || name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Nome não pode ser vazio ou nulo.");
        }

        // Remove espaços extras nas extremidades
        this.name = name.trim();
    }

    /**
     * Retorna o email da pessoa.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email com validação de formato.
     *
     * @param email email informado
     */
    public void setEmail(String email) {

        // Valida se o email é nulo ou inválido
        if (email == null ||
                !Contact.EMAIL_PATTERN.matcher(email).matches()) {

            throw new IllegalArgumentException("Email inválido.");
        }

        // Armazena email em letras minúsculas
        this.email = email.toLowerCase();
    }

    /**
     * Representação textual do objeto.
     *
     * @return string formatada da pessoa
     */
    @Override
    public String toString() {

        return String.format(
                "[%s] %s (%s)",
                getType(),
                name,
                email);
    }
}