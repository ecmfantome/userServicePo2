package dev.unchk.userservice.user.mapper;

import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImp  implements IMapper{
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public User toUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    @Override
    public List<User> toUsers(List<UserRequest> usersRequest) {
        return usersRequest.stream().map(clientRequest -> modelMapper.map(clientRequest, User.class)).toList();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> toUsersResponse(List<User> users) {
        return users.stream().map(clientRequest -> modelMapper.map(clientRequest, UserResponse.class)).toList();
    }
}
