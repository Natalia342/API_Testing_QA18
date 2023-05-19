package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestGetAllContacts {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzaWF0YWxAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2ODQ4NTc2OTYsImlhdCI6MTY4NDI1NzY5Nn0.FZ88ow-4kZo-95o68wFGi0qWevsptuPTkJyLwu7i-Ew";

    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
    @Test
    public void getAllContacts(){
        GetAllContactsDTO list = given()
                .header("Authorization",token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(GetAllContactsDTO.class);

        for(ContactDTO contact : list.getContacts()){
            System.out.println(contact.getId());
            System.out.println("******");
            System.out.println(contact.getEmail());

        }
    }
}
