package restAssured;

import com.jayway.restassured.RestAssured;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestLoginTests {

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
    @Test
    public void loginPositiveTests(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasiatal@gmail.com")
                .password("Vasia$1234")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());
    }
    @Test
    public void loginNegativeTest(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasiatalgmail.com")
                .password("Vasia$1234")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        Object message = errorDTO.getMessage();
        System.out.println(message);
        Assert.assertEquals(message, "Login or Password incorrect");
    }
}
