package com.wishlist.testsRA.userController;

import com.wishlist.dto.AuthRequestDto;
import com.wishlist.dto.AuthResponseDto;
import com.wishlist.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserControllerLoginTest extends TestBase {

    AuthRequestDto auth = AuthRequestDto.builder()
            .email("ringo@web.com")
            .password("Berlin2024!")
            .build();

    @Test
    public void loginSuccessTest() {
        AuthResponseDto dto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("/users/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(dto.getAccessToken());
    }
    @Test
    public void logoutTest() {
        given()
                .header(AUTH, "Bearer " + TOKEN)
                .when()
                .post("/users/logout")
                .then()
                .assertThat().statusCode(200);

        System.out.println("User successfully logged out.");
    }



    @Test
    public void loginSuccesTest2(){
                String responseToken=given()
                        .contentType(ContentType.JSON)
                        .body(auth)
                        .when()
                        .post("/users/login")
                        .then()
                        .assertThat().statusCode(200)
                        .body(containsString("accessToken"))
                        .extract().path("accessToken");
                        System.out.println(responseToken);
        }
    @Test
    public void loginWithWrongEmail() {
        try {
            Response response = given().body(AuthRequestDto.builder()
                            .email("dudkinaweb.de")
                            .password("Berlin2024!")
                            .build())
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/users/login");

            // Проверка статус кода
            response.then().statusCode(401);

            // Получение тела ответа в виде строки
            String responseBody = response.getBody().asString();

            // Вывод тела ответа в консоль
            System.out.println(responseBody);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    @Test
    public void loginWithWrongEmail1(){

            given().body(AuthRequestDto.builder()
                            .email("dudkina@web")
                            .password("Berlin2024!")
                            .build())
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/users/login")
                    .then()
                    .assertThat().statusCode(401)
                    .assertThat().body("message",equalTo("Invalid e-mail or password"));



   }
    @Test
    public void loginWithWrongPassword(){
        given().body(AuthRequestDto.builder()
                        .email("dudkina@web.de")
                        .password("Berlin2024")
                        .build())
                .contentType(ContentType.JSON)
                .when()
                .post("/users/login")
                .then()
                .assertThat().statusCode(401)
                .contentType(ContentType.JSON)
                .assertThat().body("message", equalTo("Invalid e-mail or password"));
    }
}
