package dev.unchk.userservice.user.service;

import dev.unchk.userservice.entitySheared.SharedHandlerError;
import dev.unchk.userservice.security.service.UserSecurity;
import dev.unchk.userservice.user.User;
import dev.unchk.userservice.user.customeException.UserNotFoundException;
import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.mapper.UserMapperImp;
import dev.unchk.userservice.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements  IService{

    private final UserRepository userRepository;
    private final UserMapperImp userMapperImp;
    private final SharedHandlerError sharedHandlerError;

    @Override
    public String saveUser(UserRequest userRequest, BindingResult bindingResult) throws RuntimeException {
        sharedHandlerError.handlerValidate(bindingResult);
        return userRepository.save(userMapperImp.toUser(userRequest)).getId();
    }

    @Override
    public List<UserResponse> findAllUser() throws RuntimeException {
        return userMapperImp.toUsersResponse(userRepository.findAll());
    }

    @Override
    public UserResponse findUserById(String userId) throws RuntimeException {
        User user = checkUser(userId);
        return  userMapperImp.toUserResponse(user);
    }

    @Override
    public Boolean updateUser(UserRequest userRequest, BindingResult bindingResult) throws RuntimeException {
        sharedHandlerError.handlerValidate(bindingResult); 
        checkUser(userRequest.getId());
        userRepository.save(userMapperImp.toUser(userRequest));
        return true;
    }

    @Override
    public Boolean deleteUser(String userId) throws RuntimeException {
        checkUser(userId);
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User checkUser(String userId) throws UserNotFoundException {
        if (userId == null) throw new UserNotFoundException();
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
