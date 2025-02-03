package dev.unchk.userservice.user.restController;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IRestController {


    public List<UserResponse> findAllUser();

    public UserResponse findUserById(String userId);

    public Boolean update(UserRequest user, BindingResult bindingResult);

    public Boolean delete(String userId);
}
