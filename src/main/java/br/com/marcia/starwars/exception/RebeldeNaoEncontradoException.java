package br.com.marcia.starwars.exception;

public class RebeldeNaoEncontradoException extends IdNaoEncontradoException {

    public RebeldeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
