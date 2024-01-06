package com.example.foodsubscription.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static java.lang.Boolean.TRUE;
import static java.util.Objects.requireNonNull;

@Slf4j
public class SslPropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        if (TRUE.equals(environment.getProperty("server.ssl.enabled", Boolean.class))) {
            log.info("Ssl system properties setting");
//            System.setProperty("javax.net.ssl.trustStore", requireNonNull(environment.getProperty("server.ssl.trust-store")));
//            System.setProperty("javax.net.ssl.trustStorePassword", requireNonNull(environment.getProperty("server.ssl.trust-store-password")));
            System.setProperty("javax.net.ssl.keyStore", requireNonNull(environment.getProperty("server.ssl.key-store")));
            System.setProperty("javax.net.ssl.keyStorePassword", requireNonNull(environment.getProperty("server.ssl.key-store-password")));
        } else {
            log.warn("Ssl disabled, not setting system properties");
        }
    }
}
