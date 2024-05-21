package com.wishlist.testsRA;

import com.wishlist.dto.AuthRequestDto;
import com.wishlist.dto.AuthResponseDto;
import com.wishlist.dto.ErrorDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends TestBase {
    //RestAssured.registerParser("text/plain", Parser.JSON); // JSON для типа контента 'text/plain'
    AuthRequestDto auth = AuthRequestDto.builder()
            .email("dudkina@web.de")
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


//    @Test
//    public void loginWithWrongEmail1(){
//
//            given().body(AuthRequestDto.builder()
//                            .email("dudkinaweb.de")
//                            .password("Berlin2024!")
//                            .build())
//                    .contentType(ContentType.JSON)
//                    .when()
//                    .post("/users/login")
//                    .then()
//                    .assertThat().statusCode(400)
//                    //.contentType(ContentType.TEXT)
//                    .assertThat().body("message",equalTo("Login or Password incorrect"));



//    }
    @Test
    public void loginWithWrongEmail2(){
        given().body(AuthRequestDto.builder()
                        .email("dudkinaweb.de")
                        .password("Berlin2024!")
                        .build())
                .contentType(ContentType.JSON)
                .when()
                .post("/users/login")
                .then()
                .assertThat().statusCode(400)
                .contentType(ContentType.TEXT)
                .body("message", equalTo("Invalid e-mail or password"));
    }
}
