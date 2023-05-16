package okhttpTests;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class okHTTPGetAllContactsTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    @Test
    public void getAllContacts() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetAllContactsDTO contacts = gson.fromJson(response.body().string(), GetAllContactsDTO.class);

        for(ContactDTO contact : contacts.getContacts()){
            System.out.println(contact.getId());
            System.out.println("******");
            System.out.println(contact.getEmail());

        }
    }
}
