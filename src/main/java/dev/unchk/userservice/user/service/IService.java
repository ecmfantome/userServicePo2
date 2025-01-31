package dev.unchk.userservice.user.service;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IService {
    String saveUser(UserRequest userRequest, BindingResult bindingResult);

    List<UserResponse> findAllUser();

    User checkUser(String userId);

    UserResponse findUserById(String userId);

    Boolean updateUser(UserRequest userRequest, BindingResult bindingResult);

    Boolean deleteUser(String userId);
}
