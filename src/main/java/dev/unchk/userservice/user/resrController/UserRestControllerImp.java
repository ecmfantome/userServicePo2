package dev.unchk.userservice.user.resrController;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestControllerImp implements IRestController{

    private final UserServiceImp userServiceImp;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(UserRequest user, BindingResult bindingResult) {
        return userServiceImp.saveUser(user, bindingResult);

    }

    @Override
    @GetMapping
    public List<UserResponse> findAllUser() {
        return  userServiceImp.findAllUser();
    }

    @Override
    @GetMapping("/{userId}")
    public UserResponse findUserById(@PathVariable String userId) {
        return userServiceImp.findUserById(userId);
    }

    @Override
    @PutMapping
    public Boolean updateUser(UserRequest user, BindingResult bindingResult) {
        return userServiceImp.updateUser(user, bindingResult);
    }

    @Override
    @DeleteMapping("/{userId}")
    public Boolean deleteUser(@PathVariable String userId) {
        return  userServiceImp.deleteUser(userId);
    }
}
