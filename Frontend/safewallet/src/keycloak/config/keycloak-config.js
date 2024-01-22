import Keycloak from 'keycloak-js'

const keycloakConfig = {
  url: 'http://localhost:8080/auth', // Keycloak server URL
  realm: 'safewallet', // Your realm
  clientId: 'safewallet-frontend', // Your client ID
};

const keycloak = new Keycloak(keycloakConfig);

export default keycloak;