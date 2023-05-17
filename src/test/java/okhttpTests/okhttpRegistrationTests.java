package okhttpTests;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class okhttpRegistrationTests {
    //нужно когда мы строим запросы содержащие реквест body
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();//когда нам нужно обьект java превратить в Gson и наоборот
    OkHttpClient client = new OkHttpClient();//отправляет запрос

    @Test
    public void registrationSuccess() throws IOException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        AuthRequestDTO dto = AuthRequestDTO.builder()
                .username("Polia"+ i + "@gmail.com" )
                .password("Pp$15" + i)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(dto), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        Assert.assertTrue(response.isSuccessful());
    }
}
