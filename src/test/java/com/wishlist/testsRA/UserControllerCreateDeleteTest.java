package com.wishlist.testsRA;
import com.wishlist.dto.AuthRequestDto;
import com.wishlist.dto.UserDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserControllerCreateDeleteTest extends TestBase {

    @Test
    public void updateUserTest() {

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

        // Тело запроса PUT
        UserDto updatedUser = UserDto.builder()
                .firstName("Paul")
                .lastName("McCar")
                .email("ringo@web.com")
                .password("Berlin2024!!")
                .build();

        Response response = given()
                .header(AUTH, "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/users/auth/me")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body:");
        System.out.println(responseBody);
    }







    @Test
    public void createNewUserPositiveTest() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        UserDto user = UserDto.builder()
                .firstName("Ringo")
                .lastName("Starr")
                .email("ringo" + randomNumber + "@web.com")
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

        // Получение всех пользователей
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
    public void deleteUserPositiveTest() {
        UserDto user = UserDto.builder()
                .firstName("John")
                .lastName("Lennon")
                .email("john@web.com")
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


        AuthRequestDto auth = AuthRequestDto.builder()
                .email("John@web.com")
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
                .delete("users/auth/me" )
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract()
                .response();
        String responseBody = response.getBody().asString();
        System.out.println("answerBody:");
        System.out.println(responseBody);


    }


}


