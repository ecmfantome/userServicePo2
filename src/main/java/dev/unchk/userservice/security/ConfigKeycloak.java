package dev.unchk.userservice.security;

import dev.unchk.userservice.config.ConfigProperties;
import lombok.AllArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    public RealmResource getRealm(){
        return keycloak().realm(configProperties.getRealmName());
    }

    public UsersResource getUserResource(){
        return getRealm().users();
    }

    public CredentialRepresentation resetPassword(String password){
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setTemporary(false);
        cred.setValue(password);
        return cred;
    }

    public String getIdClient(){
        return getRealm().clients().findByClientId(configProperties.getRealmClientId()).getFirst().getId();
    }

    public RolesResource getRolesResource(){
        return getRealm().clients().get(getIdClient()).roles();
    }

    public List<String> getRolesList(){
       return getRolesResource().list().stream().map(RoleRepresentation::getName).toList();
    }

    public boolean findRole(String role){
      return   getRolesList().contains(role);
    }
}
