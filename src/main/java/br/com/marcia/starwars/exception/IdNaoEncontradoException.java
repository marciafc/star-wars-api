package br.com.marcia.starwars.exception;

public class IdNaoEncontradoException extends RuntimeException {

    public IdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
