package okhttpTests;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactDTOResponse;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class okhttpDeleteByID {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    String id;

    @BeforeMethod
    public void preCondition() throws IOException {
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        ContactDTO contactDTO = ContactDTO.builder()
                .name("MILA")
                .lastName("Pashkova")
                .email("mila" + i + "@mail.com")
                .phone("859862345" + i)
                .address("NY")
                .description("Friend").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactDTOResponse contactDTOResponse = gson.fromJson(response.body().string(), ContactDTOResponse.class);
        String message = contactDTOResponse.getMessage();
        id = message.substring(message.lastIndexOf(' ') + 1);
    }

    @Test
    public void DeleteByID() throws IOException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        ContactDTO dto = ContactDTO.builder()
                .name("MILA")
                .lastName("Pashkova")
                .email("mila" + i + "@mail.com")
                .phone("859862345" + i)
                .address("NY")
                .description("Friend").build();

 //   RequestBody requestBody = RequestBody.create(gson.toJson(dto), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/" + id)
                .addHeader("Authorization", token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        ContactDTOResponse contactDTOResponse = gson.fromJson(response.body().string(), ContactDTOResponse.class);

        String message = contactDTOResponse.getMessage();
        System.out.println(message);
//        Assert.assertTrue(message.contains("Contact was deleted!"));

}}
