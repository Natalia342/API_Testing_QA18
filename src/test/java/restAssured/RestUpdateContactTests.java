package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestUpdateContactTests {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";
    String id;
    ContactDTO contactDTO;

    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        contactDTO= ContactDTO.builder()
                .name("Vova")
                .lastName("Kova")
                .email("mila" + i + "@mail.com")
                .phone("859862345" + i)
                .address("NY")
                .description("Friend").build();

        String message = given()
                .header("Authorization",token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(' ') + 1);
    }
    @Test
    public void updateContact(){
        contactDTO.setName("Jake");
        contactDTO.setId(id);

        given()

                .body(contactDTO)
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(200);

    }
}
