package br.com.erickrocha.contactmanager.model;

import br.com.erickrocha.contactmanager.exception.InvalidContactException;
import java.util.regex.Pattern;

/**
 * Classe que representa um contato do sistema.
 *
 * Herda da classe abstrata Person e implementa
 * a interface Searchable para permitir buscas.
 *
 * Também possui validações para telefone e email,
 * garantindo integridade dos dados.
 */
public class Contact extends Person implements Searchable {

    // Número de telefone do contato
    private String phone;

    // Contador global de instâncias criadas
    public static int instanceCount = 0;

    // Expressão regular para validação de emails
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    // Expressão regular para validação de telefones
    // Aceita apenas 10 ou 11 dígitos numéricos
    public static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}");

    /**
     * Construtor padrão.
     *
     * Cria um contato com valores default.
     */
    public Contact() throws InvalidContactException {

        this(
                "Novo Contato",
                "email@padrao.com",
                "0000000000");
    }

    /**
     * Construtor com nome e email.
     *
     * Define telefone padrão automaticamente.
     *
     * @param name  nome do contato
     * @param email email do contato
     */
    public Contact(String name, String email)
            throws InvalidContactException {

        this(name, email, "0000000000");
    }

    /**
     * Construtor principal da classe.
     *
     * @param name  nome do contato
     * @param email email do contato
     * @param phone telefone do contato
     */
    public Contact(String name, String email, String phone)
            throws InvalidContactException {

        // Inicializa atributos herdados da superclasse
        super(name, email);

        // Define telefone com validação
        setPhone(phone);

        // Incrementa contador global de objetos
        instanceCount++;
    }

    /**
     * Valida o telefone informado.
     *
     * @param phone telefone a validar
     * @throws InvalidContactException caso seja inválido
     */
    private void validatePhone(String phone)
            throws InvalidContactException {

        // Verifica se telefone é nulo
        // ou não segue o padrão esperado
        if (phone == null ||
                !PHONE_PATTERN.matcher(phone).matches()) {

            throw new InvalidContactException(
                    "Telefone deve conter 10 ou 11 dígitos numéricos.");
        }
    }

    /**
     * Retorna o tipo do objeto.
     *
     * Implementação obrigatória da classe abstrata Person.
     *
     * @return tipo da entidade
     */
    @Override
    public String getType() {
        return "Contato";
    }

    /**
     * Retorna o telefone do contato.
     *
     * @return telefone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define o telefone do contato.
     *
     * @param phone telefone informado
     * @throws InvalidContactException caso seja inválido
     */
    public void setPhone(String phone)
            throws InvalidContactException {

        // Valida telefone antes de atribuir
        validatePhone(phone);

        this.phone = phone;
    }

    /**
     * Sobrescreve a validação de email da superclasse.
     *
     * Caso o email seja inválido,
     * mantém o valor anterior.
     *
     * @param email email informado
     */
    @Override
    public void setEmail(String email) {

        try {

            // Executa validação da superclasse
            super.setEmail(email);

        } catch (IllegalArgumentException e) {

            // Mantém email anterior em caso de erro
            System.out.println(
                    "Email inválido. Mantido o anterior.");
        }
    }

    /**
     * Verifica se o contato corresponde ao termo pesquisado.
     *
     * A busca é realizada:
     * - no nome
     * - no email
     * - no telefone
     *
     * @param query termo pesquisado
     * @return true caso exista correspondência
     */
    @Override
    public boolean matches(String query) {

        // Converte busca para minúsculo
        // para evitar diferença entre maiúsculas/minúsculas
        String lowerQuery = query.toLowerCase();

        return name.toLowerCase().contains(lowerQuery) ||

                email.toLowerCase().contains(lowerQuery) ||

                phone.contains(lowerQuery);
    }

    /**
     * Representação textual do contato.
     *
     * @return string formatada do contato
     */
    @Override
    public String toString() {

        return String.format(
                "%s | Telefone: %s | Email: %s",
                super.toString(),
                phone,
                email);
    }
}