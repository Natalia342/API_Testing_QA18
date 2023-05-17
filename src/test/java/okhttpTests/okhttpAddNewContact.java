package okhttpTests;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ContactDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class okhttpAddNewContact {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    @Test
    public void addNewContactSuccess() throws IOException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        ContactDTO dto = ContactDTO.builder()
                .name("MILA")
                .lastName("Pashkova")
                .email("mila" + i + "@mail.com")
                .phone("859862345" + i)
                .address("NY")
                .description("Friend").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(dto), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .build();

        Response response = client.newCall(request).execute();


    }
    }

