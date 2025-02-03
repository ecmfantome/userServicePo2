package dev.unchk.userservice.security.service;

import dev.unchk.userservice.config.ConfigProperties;
import dev.unchk.userservice.security.ConfigKeycloak;
import dev.unchk.userservice.security.model.UserLogin;
import dev.unchk.userservice.user.dto.UserRequest;
import dev.unchk.userservice.user.dto.UserResponse;
import dev.unchk.userservice.user.service.IService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserSecurity implements IUserService {

    private ConfigProperties configProperties;
    private ConfigKeycloak configKeycloak;
    private IService userService;

    @Override
    public ResponseEntity<Object> signIn(UserLogin userLogin) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> valueXForm = new LinkedMultiValueMap<>();
        valueXForm.add("grant_type", "password");
        valueXForm.add("username", userLogin.email());
        valueXForm.add("password", userLogin.password());
        valueXForm.add("client_id", configProperties.getRealmClientId());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(valueXForm, headers);
        ResponseEntity<Object> result;
        Map<String, Object> dataLogin = new HashMap<>();
        try {
            result = restTemplate.exchange(configProperties.getAuthUrl(), HttpMethod.POST, entity, Object.class);
            UserResponse userResponse = userService.findUserByEmail(userLogin.email());
            dataLogin.put("user", userResponse);
            dataLogin.put("token", result.getBody());
            return new ResponseEntity<>(dataLogin, HttpStatus.OK);
        } catch (RestClientException e) {
            throw new RuntimeException("erreur login");
        }
    }


    @Override
    public ResponseEntity<String> signUp(UserRequest user, BindingResult bindingResult) {
        UserResponse userResponse = userService.findUserByEmail(user.getMail());
        if (userResponse != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce user existe déja...!!!");
        //
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getMail());
        userRepresentation.setEmail(user.getMail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        String userKeycloackId;
        // Create User
        UsersResource usersRessource = configKeycloak.getUserResource();
        Response response = usersRessource.create(userRepresentation);
        //
        if (response.getStatus() == 201) {
            userKeycloackId = CreatedResponseUtil.getCreatedId(response);
            try {
                UserResource newUserCreated = usersRessource.get(userKeycloackId);
                //Affectation role
                addRoleUser(newUserCreated, user.getRole().toString());
                //Reset password
                newUserCreated.resetPassword(configKeycloak.resetPassword(user.getPassword()));
                //Create user in your BD
                String idUserBD = userService.save(user, bindingResult);
                return new ResponseEntity<>(idUserBD, HttpStatus.CREATED);
            } catch (RuntimeException e) {
                usersRessource.delete(userKeycloackId);
                throw e;
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void addRoleUser(UserResource userResource, String role) {
        if (configKeycloak.findRole(role)) {
            RoleRepresentation roleRepresentation = configKeycloak.getRolesResource().get(role).toRepresentation();
            List<RoleRepresentation> roleToAdd = new LinkedList<>();
            roleToAdd.add(roleRepresentation);
            userResource.roles().clientLevel(configKeycloak.getIdClient()).add(roleToAdd);
        } else
            throw new RuntimeException("Une erreur avec le role !!!");
    }
}
