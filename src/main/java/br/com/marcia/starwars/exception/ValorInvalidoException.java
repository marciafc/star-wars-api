package br.com.marcia.starwars.exception;

public class ValorInvalidoException extends RuntimeException {

    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}
