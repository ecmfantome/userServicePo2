package dev.unchk.userservice.user.dto;

import dev.unchk.userservice.user.User.GenderEnum;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private String password;
    private GenderEnum gender;
    //Fields Relations
}
