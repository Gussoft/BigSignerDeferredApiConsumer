package pe.bigprime.contoller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConsumerApiToken {
    public static void main(String[] args) {
        //CON EL TOKEN GENERADO EN REQUESTTOKEN ENVIAMOS EL HASHCODE DEL DOCUEMNTO Y NOS DEVOLVERA EL HASHFIRMADO Y EL HASH
        String token = "eyJraWQiOiJMaElPd25ka3BuQ1MwcFR1cWd2TnJEUm5iRFwvUDlZQ1BGYlZHUkNWakdzWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4YTE5MDc5Yi0wNjUxLTQzMTgtYjY5ZC0zZWFmYjllNmU5YmIiLCJhdWQiOiIxNWRnNWRpZWlpdW9tMWVsMnRucWpiMmZiMyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJldmVudF9pZCI6IjMxY2I5OTlhLTQyZDgtNGY4YS05ZDQ0LTczZTE3NGU2YTY1NiIsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNjI5MzA0Mzk4LCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV9CSWZmTG01TUQiLCJjb2duaXRvOnVzZXJuYW1lIjoiYW5hbGlzdGEyIiwiZXhwIjoxNjI5MzA3OTk4LCJpYXQiOjE2MjkzMDQzOTgsImVtYWlsIjoiYW5hbGlzdGEyQGJpZ3ByaW1lLnBlIn0.RGB-w8o-EU9pR57_cP3VSszWcruNTgWycn_XpfkJW2GJihU-3NjBZ8eSILWm1SxK3rBf_HilG5ziLeNR746COT5RYKCYvmFwB19YofbiNZkt-jlxzTX3eCcoFZYcSWLKE4EvBYgGptfPX50YdCphWVwFYVs704iDg-MGwRcI1JqueCb6cRt3zAfhYDjaXDbpcFJFRVnCZxkVLhUNiM5kjPR2IA791EmAZ8xmRiqxvdyJcSeEaxB8fduJQ28gDUTGbcLLVhV5Ju7YhvXAJPiBUvNMZTm-MSJ7qMtxke2RFMTzcSlZjOWMZtU59ePrpMjmPHIQTHVqLUz-HbU4eTE7_w";
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
    }
}
