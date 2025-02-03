package dev.unchk.userservice.security.service;

import dev.unchk.userservice.security.model.UserLogin;
import dev.unchk.userservice.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IUserService {
    public ResponseEntity<Object> signIn(UserLogin userLogin);

    public ResponseEntity<String> signUp(UserRequest userRequest, BindingResult bindingResult);
}
