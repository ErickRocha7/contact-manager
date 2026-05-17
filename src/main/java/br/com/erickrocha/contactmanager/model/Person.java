package br.com.erickrocha.contactmanager.model;

/**
 * Superclasse abstrata que representa uma pessoa.
 * Garante que nome e email nunca sejam nulos ou inválidos,
 * mantendo a invariante do objeto.
 */
public abstract class Person {
    protected String name;
    protected String email;

    public Person() {
        this.name = "Sem nome";
        this.email = "sem@email.com";
    }

    public Person(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !Contact.EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email.toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", getType(), name, email);
    }
}