package br.edu.ifrs.miguelzk.infrastructure.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {
    public ObjetoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
