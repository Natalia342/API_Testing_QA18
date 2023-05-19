package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestAddNewContactTests {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
    @Test
    public void addNewContactTest(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        ContactDTO dto = ContactDTO.builder()
                .name("Vova")
                .lastName("Kova")
                .email("mila" + i + "@mail.com")
                .phone("859862345" + i)
                .address("NY")
                .description("Friend").build();

        given()
                .header("Authorization",token)
                .body(dto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message",containsString("Contact was added!"));
    }
}
