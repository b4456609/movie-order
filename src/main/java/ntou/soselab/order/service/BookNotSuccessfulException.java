package ntou.soselab.order.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookNotSuccessfulException extends RuntimeException {
    public BookNotSuccessfulException(String message) {
        super(message);
    }
}
