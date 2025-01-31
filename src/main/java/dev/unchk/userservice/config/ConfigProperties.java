package dev.unchk.userservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "env.config")
@AllArgsConstructor
@Getter
public class ConfigProperties {

    private final String serverUrl;
    private final String authUrl;
    private final String realmName;
    private final String realmClientId;
    private final String realmAdminId;
    private final String realmClientSecret;
}
