package desafio.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {
        ProblemDetail problem = new ProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor",
                OffsetDateTime.now()
        );

        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Classe para representar detalhes do problema
    @Data // Gera getters, setters, equals, hashCode e toString
    @AllArgsConstructor // Gera o construtor com todos os argumentos
    @NoArgsConstructor // Gera construtor sem argumentos (recomendado para serialização)
    public static class ProblemDetail {
        private int status;
        private String message;
        private OffsetDateTime timestamp;
        }
    }

