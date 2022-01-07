package pe.bigprime.contoller;

import com.amazonaws.http.HttpMethodName;
import pe.bigprime.utils.ApiGatewayResponse;
import java.io.ByteArrayInputStream;
import java.net.URI;

public class BigSigerRest {

    static final String AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
    static final String AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");
    static final String AWS_REGION = "us-east-1";
    static final String AWS_GATEWAY_API = "https://hlxis7wyh2.execute-api.us-east-1.amazonaws.com/demo/signer";//SI QUITAMOS EL /SIGNER, DEBEMOS ENVIARLO LUEGO POR EL EXECUTE! line 25

    public static void main(String[] args) {
        String hashCode = "{ \"hash\" : \"MUswGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAvBgkqhkiG9w0BCQQxIgQgM+sSU7ibkOA0Qc1Xy6gLeakgoMzxHrcJkK0bTKw1QEc=\"}";

        try{
            JsonApiGateway getHash = new JsonApiGateway(
                    AWS_ACCESS_KEY,AWS_SECRET_KEY,null, //RESOURCEPATH SERIA EL NOMBRE DE LA ETAPA EN ESTE CASO LO ENVIAMOS TODO EN EL AWS API GATEWAY, line 25
                    AWS_REGION, new URI(AWS_GATEWAY_API)
            );
            ApiGatewayResponse response = getHash.execute(HttpMethodName.POST,null, new ByteArrayInputStream(hashCode.getBytes()));//AQUI.
            System.out.println(response.getBody()); //RESOURCEPATH = "/SIGNER", YA QUE LO ENVIAMOS TODO EN LA RUTA NO SERIA NECESARIO ENVIARLO AQUI. line 25
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
