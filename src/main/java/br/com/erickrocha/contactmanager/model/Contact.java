package br.com.erickrocha.contactmanager.model;

import br.com.erickrocha.contactmanager.exception.InvalidContactException;
import java.util.regex.Pattern;

/**
 * Representa um contato com telefone. Herda de Person (abstrata) e implementa
 * Searchable. Garante que todos os campos estejam sempre válidos.
 */
public class Contact extends Person implements Searchable {
    private String phone;
    public static int instanceCount = 0;

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}");

    public Contact() throws InvalidContactException {
        this("Novo Contato", "email@padrao.com", "0000000000");
    }

    public Contact(String name, String email) throws InvalidContactException {
        this(name, email, "0000000000");
    }

    public Contact(String name, String email, String phone)
            throws InvalidContactException {
        super(name, email);
        setPhone(phone);
        instanceCount++;
    }

    private void validatePhone(String phone) throws InvalidContactException {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidContactException(
                    "Telefone deve conter 10 ou 11 dígitos numéricos.");
        }
    }

    @Override
    public String getType() {
        return "Contato";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws InvalidContactException {
        validatePhone(phone);
        this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
        try {
            super.setEmail(email);
        } catch (IllegalArgumentException e) {
            System.out.println("Email inválido. Mantido o anterior.");
        }
    }

    @Override
    public boolean matches(String query) {
        String lowerQuery = query.toLowerCase();
        return name.toLowerCase().contains(lowerQuery) ||
                email.toLowerCase().contains(lowerQuery) ||
                phone.contains(lowerQuery);
    }

    @Override
    public String toString() {
        return String.format("%s | Telefone: %s | Email: %s",
                super.toString(), phone, email);
    }
}