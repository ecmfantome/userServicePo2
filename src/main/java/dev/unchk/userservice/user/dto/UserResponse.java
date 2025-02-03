package dev.unchk.userservice.user.dto;

import dev.unchk.userservice.user.User.GenderEnum;
import dev.unchk.userservice.user.User.RoleEnum;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String lastName;
    private Integer age;
    private String profileImage;
    private String mail;
    private GenderEnum gender;
    private RoleEnum Role;
    //Fields Relations
}
