package dev.unchk.userservice.user.resrController;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IRestController {

    public String saveUser(UserRequest user, BindingResult bindingResult);

    public List<UserResponse> findAllUser();

    public UserResponse findUserById(String userId);

    public Boolean updateUser(UserRequest user, BindingResult bindingResult);

    public Boolean deleteUser(String userId);
}
