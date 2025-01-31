package dev.unchk.userservice.entitySheared.customeExecption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SharedValidateFieldError extends RuntimeException  {
    public SharedValidateFieldError(String message) {
        super(message);
    }
}
