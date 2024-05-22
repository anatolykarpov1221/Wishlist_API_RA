package com.wishlist.testsRA;
import com.wishlist.dto.AuthResponseDto;
import com.wishlist.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import com.google.gson.Gson;

public class UserControllerTest extends TestBase{
    //RestAssured.defaultParser = Parser.JSON;


    @Test
    public void createNewUserTest() {
        // Создаем объект пользователя с реальными данными
        UserDto user = UserDto.builder()
                .firstName("John")
                .lastName("Lennon")
                .email("john@example.com")
                .password("Berlin2024!")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users/register")
                .then()
                .assertThat().statusCode(201) // Ожидаемый статус код успешного запроса
                .log().all(); // Логируем все детали запроса и ответа
    }

}
