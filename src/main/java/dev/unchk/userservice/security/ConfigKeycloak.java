package dev.unchk.userservice.security;

import dev.unchk.userservice.config.ConfigProperties;
import lombok.AllArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigKeycloak {
   private ConfigProperties configProperties;

    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(configProperties.getServerUrl())
                .realm(configProperties.getRealmName())
                .clientId(configProperties.getRealmAdminId())
                .clientSecret(configProperties.getRealmClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
