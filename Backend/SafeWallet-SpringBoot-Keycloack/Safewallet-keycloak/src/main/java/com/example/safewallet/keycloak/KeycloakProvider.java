package com.example.safewallet.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.context.annotation.Bean;

import java.security.Key;


public class KeycloakProvider {

    private static final String SERVER_URL = "http://localhost:8080/"; // aca se define la URL del keycloak
    private static final String REALM_NAME = "safewallet";
    private static final String REALM_MASTER = "master";
    private static final String REALM_CLI = "admin-cli"; // DEL MASTER!!
    private static final String USER_CONSOLE = "admin";
    private static final String USER_PASSWORD = "admin";
    private static final String CLIENT_ID = "frontend_client";

    //CREAMOS NUESTRO KEYCL
    public static RealmResource getRealmResource () {
        Keycloak keycloak= KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_MASTER)
                .clientId(REALM_CLI)
                .username(USER_CONSOLE)
                .password(USER_PASSWORD)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10) // permitimos un max de 10 conexiones a la vez
                        .build())
                .build();

        return keycloak.realm(REALM_NAME);
    }

    public static Keycloak getKeycloak(){
         Keycloak keycloak= KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_MASTER)
                .clientId(REALM_CLI)
                .username(USER_CONSOLE)
                .password(USER_PASSWORD)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10) // permitimos un max de 10 conexiones a la vez
                        .build())
                .build();
    return keycloak;

    }


    // Creamos el recurso para manejar los usuarios
    public static UsersResource getUserResource(){
        RealmResource  resource = getRealmResource();
        return resource.users();
    }

    public static RealmResource getResource(){
        Keycloak keycloak = Keycloak.getInstance(SERVER_URL, REALM_NAME, CLIENT_ID,null);
        RealmResource realmResource = keycloak.realm(REALM_NAME);
        return  realmResource;
    }




}
