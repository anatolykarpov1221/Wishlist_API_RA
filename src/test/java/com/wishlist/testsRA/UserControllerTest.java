package com.wishlist.testsRA;
import com.wishlist.dto.AuthRequestDto;
import com.wishlist.dto.AuthResponseDto;
import com.wishlist.dto.UserDto;
import com.wishlist.dto.WishlistDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.List;
import static ch.qos.logback.classic.spi.CallerData.extract;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserControllerTest extends TestBase {

    @Test
    public void deleteUserTest1() {
        // Аутентификация и получение токена
        AuthRequestDto auth = AuthRequestDto.builder()
                .email("ringo@web.com")
                .password("Berlin2024!")
                .build();

        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .post("/users/login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");

        Response response = given()
                .header(AUTH, "Bearer " + token)
                .when()
                .delete("/users/auth/me")
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract()
                .response();
        String responseBody = response.getBody().asString();
        System.out.println("answerBody:");
        System.out.println(responseBody);
    }





    @Test
    public void createNewUserPositiveTest() {
        // Создаем объект пользователя с реальными данными
        UserDto user = UserDto.builder()
                .firstName("Ringo")
                .lastName("Starr")
                .email("Rringo@web.com")
                .password("Berlin2024!")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users/register")
                .then()
                .log().all()
                .assertThat().statusCode(201);

    }
    @Test
    public void createNewUserNegativeTest() {
        UserDto existingUser = UserDto.builder()
                .firstName("Ringo")
                .lastName("Starr")
                .email("ringo@web.com")
                .password("Berlin2024!")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(existingUser)
                .when()
                .post("/users/register")
                .then()
                .log().all()
                .assertThat().statusCode(409)
                .body("message", equalTo("User with email ringo@web.com already exists"));
    }

    @Test
    public void getAllUsersSuccessTest() {
        // Аутентификация и получение токена
        AuthRequestDto auth = AuthRequestDto.builder()
                .email("alex.stone@mail.com")
                .password("qwerty123")
                .build();

        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .post("/users/login") // Предположим, что эндпоинт для аутентификации - "/login"
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");

        // Получение всех пользователей с использованием полученного токена
        List<UserDto> allusers = given()
                .header(AUTH, "Bearer " + token)
                .when()
                .get("users")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .jsonPath()
                .getList("", UserDto.class);

        for (UserDto user : allusers) {
            System.out.println(user);
        }
    }

    @Test
    public void deleteUserNegativeTest() {

        AuthRequestDto auth = AuthRequestDto.builder()
                .email("ringo@web.com")
                .password("Berlin2024!")
                .build();

        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .post("/users/login")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .path("accessToken");

        Response response = given()
                .header(AUTH, "Bearer " + token)
                .when()
                .delete("users/auth/me" )
                .then()
                .log().all()
                .assertThat().statusCode(403)
                .extract()
                .response();
        String responseBody = response.getBody().asString();
        System.out.println("answerBody:");
        System.out.println(responseBody);


    }


}


