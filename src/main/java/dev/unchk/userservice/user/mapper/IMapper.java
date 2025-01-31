package dev.unchk.userservice.user.mapper;
import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.User;

import java.util.List;

public interface IMapper {

    User toUser(UserRequest userRequest);

    List<User> toUsers(List<UserRequest> usersRequest);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUsersResponse(List<User> users);
}
