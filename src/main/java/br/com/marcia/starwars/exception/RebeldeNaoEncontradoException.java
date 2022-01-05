package br.com.marcia.starwars.exception;

public class RebeldeNaoEncontradoException extends RuntimeException {

    public RebeldeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
