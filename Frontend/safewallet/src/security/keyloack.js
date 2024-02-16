import Keycloak from "keycloak-js";

async function keylockInit() {
  const keycloak = new Keycloak({
    url: "http://localhost:8080",
    realm: "safewallet",
    clientId: "frontend_client",
  });

  try {
    const authenticated = await keycloak.init({
      onLoad: 'login-required',
      silentCheckSsoRedirectUri: `${location.origin}/silent-check-sso.html`
  });
    console.log(`User is ${authenticated ? 'authenticated' : 'not authenticated'}`);
    localStorage.setItem('keycloak-token', JSON.stringify(keycloak.token));
    
} catch (error) {
    console.error('Failed to initialize adapter:', error);
}
  
}

export default keylockInit