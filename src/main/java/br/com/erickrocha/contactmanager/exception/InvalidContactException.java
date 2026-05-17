package br.com.erickrocha.contactmanager.exception;

// Exceção personalizada (checked) para dados de contato inválidos
public class InvalidContactException extends Exception {
    public InvalidContactException(String mensagem) {
        super(mensagem);
    }
}