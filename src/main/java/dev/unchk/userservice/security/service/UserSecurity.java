package dev.unchk.userservice.security.service;

import dev.unchk.userservice.config.ConfigProperties;
import dev.unchk.userservice.security.model.UserLogin;
import dev.unchk.userservice.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserSecurity implements IUserService {

    private ConfigProperties configProperties;

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
//            dataLogin.put("client", userApp.get().getClient());
            dataLogin.put("token", result.getBody());
            return new ResponseEntity<>(dataLogin, HttpStatus.OK);
        } catch (RestClientException e) {
           throw new RuntimeException("erreur login");
        }
    }


    @Override
    public User signUp(User user) {
        return null;
    }
}
