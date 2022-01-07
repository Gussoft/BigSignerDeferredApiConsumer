package pe.bigprime.contoller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.RespondToAuthChallengeRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BigSignerCognito {
    static final String AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
    static final String AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");

    static final String CLIENT_ID = System.getenv("CLIENT_ID");
    static final String GROUP_USER = System.getenv("GROUP_USER");

    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        try{
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
            AWSCognitoIdentityProvider provider = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(Regions.US_EAST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
            Map<String, String> params = new HashMap<>();
            params.put("USERNAME", "an2");//signhash
            params.put("PASSWORD", "A1s.");//myhmyx-8vyRzi-dyjvupss

            AdminInitiateAuthRequest admin = new AdminInitiateAuthRequest().withClientId(CLIENT_ID)
                    .withUserPoolId(GROUP_USER).withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                    .withAuthParameters(params);

            AdminInitiateAuthResult result = provider.adminInitiateAuth(admin);
          //PARA FORZAR EL CAMBIO DE CLAVE DE PRIMER USO COMENTAR EL IF, Y SEGUIR CON LO SIGUIENTE
            if(result != null) {
                String token = result.getAuthenticationResult().getIdToken();
                String accessToken = result.getAuthenticationResult().getAccessToken();
                String refresh = result.getAuthenticationResult().getRefreshToken();

                System.out.println("Exito! : " + result.toString());
                System.out.println("Token de autenticacion : " + token);
                System.out.println("Token Access : " + accessToken);
                System.out.println("Refresh Token : " + refresh);

            }/*
                String hashCode = "{ \"hash\" : \"MUswGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAvBgkqhkiG9w0BCQQxIgQgM+sSU7ibkOA0Qc1Xy6gLeakgoMzxHrcJkK0bTKw1QEc=\"}";
                Client client = Client.create();
                WebResource webResource = client.resource("https://w272hxxtpa.execute-api.us-east-1.amazonaws.com/test/hash");
                ClientResponse response = webResource.accept("application/json")
                        .header("bigprime", token).post(ClientResponse.class, hashCode);

                if (response.getStatus() != 200){
                    System.out.println("Failed! Error : " + response.getStatus());
                    System.out.println("Error : " + response.getEntity(String.class));
                }else{
                    System.out.println("Validado!");
                    String salida = response.getEntity(String.class);

                    //JSONObject obj = new JSONObject(salida);
                    System.out.println(salida);
                }

            }else{
                System.out.println("Error! en la autenticacion");
            }

            //SIGUIENTE
            //CAMBIAR CONTRASEÑA DE PRIMER USO
/*            Map<String, String> session = new HashMap<>();
            session.put("USERNAME", "a2");//signhash
            session.put("PASSWORD", "nuevaclave.");//myhmyx-8vyRzi-dyjvupxx

            RespondToAuthChallengeRequest newClave = new RespondToAuthChallengeRequest()
                    .withChallengeName("NEW_PASSWORD_REQUIRED")
                    .withClientId(CLIENT_ID).withChallengeResponses(session)
                    .withSession(result.getSession());
            provider.respondToAuthChallenge(newClave);
*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
