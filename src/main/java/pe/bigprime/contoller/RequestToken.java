package pe.bigprime.contoller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RequestToken {
    public static void main(String[] args) {
        //SOLICITAR TOKEN A LA PAI EN LAMBDA
        String login = "{ \"USERNAME\" : \"an2\", \"PASSWORD\":\"A1.\" }";
        Client client = Client.create();
        WebResource webResource = client.resource("https://35or0kn9kd.execute-api.us-east-1.amazonaws.com/api/token");
        ClientResponse response = webResource.accept("application/json")
                .post(ClientResponse.class, login);

        if (response.getStatus() != 200){
            System.out.println("Failed! Error : " + response.getStatus());
            System.out.println("Error : " + response.getEntity(String.class));
        }else{
            System.out.println("Validado!");
            String salida = response.getEntity(String.class);

            //TOKEN VALIDO
            System.out.println(salida);
        }
    }
}
