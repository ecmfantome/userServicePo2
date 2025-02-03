package dev.unchk.userservice.user.dto;

import dev.unchk.userservice.user.User;
import dev.unchk.userservice.user.User.GenderEnum;
import dev.unchk.userservice.user.User.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
public class UserRequest {
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotNull @Min(18)  @Max(66)
    private Integer age;
    private String profileImage;
    @Email
    private String mail;
    @NotNull
    private String password;
    @NotNull
    private GenderEnum gender;
    @NotNull
    private RoleEnum Role;

    //Fields Relations
}
