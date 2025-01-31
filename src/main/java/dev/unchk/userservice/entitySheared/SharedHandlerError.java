package dev.unchk.userservice.entitySheared;

import dev.unchk.userservice.entitySheared.customeExecption.SharedValidateFieldError;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class SharedHandlerError {
    public void handlerValidate(BindingResult bindingResult) {
        Map<String, String> errorsMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
            throw new SharedValidateFieldError(errorsMap.toString());
        }
    }
}
