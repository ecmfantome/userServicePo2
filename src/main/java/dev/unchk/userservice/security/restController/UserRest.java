package dev.unchk.userservice.security.restController;


import dev.unchk.userservice.security.model.UserLogin;
import dev.unchk.userservice.security.service.IUserService;
import dev.unchk.userservice.user.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@AllArgsConstructor
public class UserRest {
    private final IUserService userService;


    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody UserLogin userLogin) {
        return userService.signIn(userLogin);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserRequest userRequest, BindingResult bindingResult) {

        return userService.signUp(userRequest, bindingResult);
    }


}
