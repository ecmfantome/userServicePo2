package dev.unchk.userservice.security.service;

import dev.unchk.userservice.security.model.UserLogin;
import dev.unchk.userservice.user.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<Object> signIn(UserLogin userLogin);
    public User signUp(User user);
}
