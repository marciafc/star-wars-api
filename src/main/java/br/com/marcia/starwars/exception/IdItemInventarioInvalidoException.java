package br.com.marcia.starwars.exception;

public class IdItemInventarioInvalidoException extends RuntimeException {

    public IdItemInventarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}
