package dev.unchk.userservice.user.restController;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestControllerImp implements IRestController {

    private final UserServiceImp userServiceImp;


    @Override
    @GetMapping
    public List<UserResponse> findAllUser() {
        return userServiceImp.findAllUser();
    }

    @Override
    @GetMapping("/{userId}")
    public UserResponse findUserById(@PathVariable String userId) {
        return userServiceImp.findUserById(userId);
    }

    @Override
    @PutMapping
    public Boolean update(UserRequest user, BindingResult bindingResult) {
        return userServiceImp.updateUser(user, bindingResult);
    }

    @Override
    @DeleteMapping("/{userId}")
    public Boolean delete(@PathVariable String userId) {
        return userServiceImp.deleteUser(userId);
    }
}
