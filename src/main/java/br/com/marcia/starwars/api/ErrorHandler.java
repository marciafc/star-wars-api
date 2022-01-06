package br.com.marcia.starwars.api;

import br.com.marcia.starwars.exception.IdNaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    private final String BAD_REQUEST_MESSAGE = "Requisição inválida.";
    private final String INTERNAL_SERVER_ERROR_MESSAGE = "Ocorreu um erro no servidor.";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdNaoEncontradoException.class)
    public ErrorResponse idNaoEncontradoException(IdNaoEncontradoException ex) {
        log.info("Erro de requisição inválida.", ex);
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException ex) {
        log.info("Erro de requisição inválida.", ex);
        return handleBadRequest(ex);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        log.error("Erro não tratado.", ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleArgumentValidationException(MethodArgumentNotValidException ex) {
        log.info("Erro de requisição inválida.", ex);
        return handleBadRequest(ex.getBindingResult());
    }

    private ErrorResponse handleBadRequest(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult
                .getFieldErrors()
                .stream()
                .map(e -> new FieldError(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponseWithFields(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE, errors);
    }

    @Getter
    class ErrorResponseWithFields extends ErrorResponse {
        private List<FieldError> erros;

        public ErrorResponseWithFields(HttpStatus status, String mensagem, List<FieldError> erros) {
            super(status, mensagem);
            this.erros = erros;
        }
    }

    @Getter
    class ErrorResponse {
        private Integer status;
        private String erro;
        private String mensagem;
        private Date timestamp;

        public ErrorResponse(HttpStatus status, String mensagem) {
            this.status = status.value();
            this.erro = status.getReasonPhrase();
            this.mensagem = mensagem;
            this.timestamp = new Date();
        }
    }

    @AllArgsConstructor
    @Getter
    class FieldError {
        private String campo;
        private String mensagem;
    }
}

